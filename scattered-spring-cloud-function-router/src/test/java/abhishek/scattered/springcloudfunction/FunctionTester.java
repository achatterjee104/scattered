package abhishek.scattered.springcloudfunction;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import org.springframework.http.*;
import org.springframework.util.SocketUtils;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

@FunctionalSpringBootTest
public class FunctionTester {

    /*@Test
    public void sampleTest() {
        try (ConfigurableApplicationContext context = new SpringApplicationBuilder(
                TestChannelBinderConfiguration.getCompleteConfiguration(
                        FunctionTester.class))
                .run("--spring.cloud.function.scan.packages=abhishek.scattered.springcloudfunction",
                        "--spring.cloud.stream.function.routing.enabled=true")) {

            InputDestination source = context.getBean(InputDestination.class);
            OutputDestination target = context.getBean(OutputDestination.class);

            source.send(new GenericMessage<>("hello".getBytes()));
            Assert.isTrue(target.receive().getPayload().equals("hello123".getBytes()), "assert failure");
        }
    }*/



    @Test
    public void testAPI() throws URISyntaxException {

        SpringApplication.run(Application.class,
                "--spring.cloud.function.scan.packages=abhishek.scattered.springcloudfunction",
                "--spring.cloud.stream.function.routing.enabled=true");

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String port = "8080";

        HttpHeaders headers = new HttpHeaders();
        headers.add("spring.cloud.function.definition", "coreFunction");

        RequestEntity<String> requestEntity = new RequestEntity<>("abhishek", headers, HttpMethod.POST, new URI("http://localhost:" + port + "/functionRouter"));
        ResponseEntity<String> response = testRestTemplate.exchange(requestEntity, String.class);
        assertThat(response.getBody()).isEqualTo("abhishek123");

        response = testRestTemplate.exchange(requestEntity, String.class);
        assertThat(response.getBody()).isEqualTo("abhishek123");
    }
}
