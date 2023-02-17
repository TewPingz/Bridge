package net.pixteria.bridge;

import org.redisson.api.RedissonClient;

import java.time.Duration;

final class Example {

    public static void main(String[] args) {
        final RedissonClient redis = null;
        final var instanceId = "skyblock-1";
        final var pipeline = new BridgePipeline(new BridgeRedis(redis, "test-message"), instanceId)
            .filter(message -> message.instanceId().startsWith("skyblock"));
        pipeline.register(TestMessage.Request.class, message -> {
            assert message.test.equals("ping");
            message.reply(new TestMessage.Response("pong"));
        });
        pipeline.call(new TestMessage.Request("ping"), Duration.ofSeconds(1L))
            .thenAccept(response -> {
                assert response.test2.equals("pong");
            });
    }

    private static final class TestMessage {
        private static final class Request extends BridgeMessageResponsible<Response> {
            private final String test;

            public Request(final String test) {
                this.test = test;
            }
        }

        private static final class Response {
            private final String test2;

            public Response(final String test2) {
                this.test2 = test2;
            }
        }
    }
}
