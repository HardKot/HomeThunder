package com.homethunder.homethunder;

public sealed class Errors {
    public int code;
    public int httpCode;
    public String payload;

    public static final class PayloadError extends Errors {
        public int code = 1001;
        public int httpCode = 400;

        public PayloadError(String payload) {
            this.payload = payload;
        }

    }
    public static final class EntityExistsError extends Errors {
        public int code = 1002;
        public int httpCode = 409;
    }
    public static final class NoAccessError extends Errors {
        public int code = 1003;
        public int httpCode = 403;
    }

}
