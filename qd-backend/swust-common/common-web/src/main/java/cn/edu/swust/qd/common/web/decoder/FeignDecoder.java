package cn.edu.swust.qd.common.web.decoder;

import cn.edu.swust.qd.common.result.Result;
import cn.hutool.http.HttpStatus;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
public class FeignDecoder implements Decoder {

    private final SpringDecoder decoder;

    public FeignDecoder(SpringDecoder decoder) {
        this.decoder = decoder;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        Method method = response.request().requestTemplate().methodMetadata().method();
        boolean notTheSame = method.getReturnType() != Result.class;
        if (notTheSame) {
            Type newType = new ParameterizedType() {
                @Override
                public Type[] getActualTypeArguments() {
                    return new Type[]{type};
                }

                @Override
                public Type getRawType() {
                    return Result.class;
                }

                @Override
                public Type getOwnerType() {
                    return null;
                }
            };
            Result<?> result = (Result<?>) this.decoder.decode(response, newType);
            if (Result.isSuccess(result)) {
                return result.getData();
            } else {
                throw new DecodeException(HttpStatus.HTTP_INTERNAL_ERROR, result.getMsg(), response.request());
            }
        }
        return this.decoder.decode(response, type);
    }
}
