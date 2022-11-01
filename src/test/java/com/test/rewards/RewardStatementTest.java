package com.test.rewards;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.rewards.model.Money;
import com.test.rewards.model.UserPayment;
import com.test.rewards.model.UserPoints;
import com.test.rewards.repository.RewardRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RewardStatementTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    int[] userIds = {12,19,25,20,13,4,26,2,5,26,14,27,29,27,15,18,4,25,23,9,4,16,7,14,26,1,1,27,17,24,12,30,2,25,7,5,21,21,23,8,16,23,28,19,21,11,20,18,18,5,16,17,22,23,9,22,5,5,13,12,4,4,3,29,30,19,20,27,23,21,5,26,26,26,13,2,19,6,18,29,29,21,17,17,28,3,19,17,5,20,3,29,13,27,29,4,23,10,30,27,2,5,5,12,15,21,14,14,21,24,15,15,28,24,23,24,26,11,16,22,27,29,25,25,29,6,27,19,3,4,15,21,27,5,14,11,11,27,13,2,6,4,30,22,20,26,22,24,12,16};
    int[] amounts = {773,129,867,926,58,340,723,43,353,734,248,865,554,849,592,778,407,942,345,811,737,390,283,152,9,63,193,347,94,535,321,450,739,126,798,86,447,547,309,84,671,995,890,480,230,124,311,279,222,705,350,609,608,149,606,777,90,38,478,215,746,604,927,665,586,83,10,153,12,631,501,446,660,925,267,529,88,189,18,314,933,562,111,934,471,470,260,139,270,210,449,286,798,1000,212,776,327,852,462,745,897,137,469,87,771,442,622,738,162,289,266,591,210,347,312,699,85,361,379,388,248,935,214,367,138,489,350,164,772,887,250,4,281,219,32,731,366,526,741,378,359,116,47,230,355,971,173,774,667,573};

    @Test
    public void testCreateStatement() throws Exception {
        for (int i=0; i<userIds.length; i++) {
            UserPayment userPayment = new UserPayment();
            userPayment.setUserId(String.valueOf(userIds[i]));
            userPayment.setPayment(new Money((double)amounts[i]));
            HttpEntity<UserPayment> entity = new HttpEntity<>(userPayment, headers);

            ResponseEntity<UserPayment> response = restTemplate.exchange(
                    createURLWithPort("/reward"), HttpMethod.POST, entity, UserPayment.class);

            assertEquals(response.getStatusCode().value(), 200);
        }
        ResponseEntity<UserPoints> rewardResponse = restTemplate.exchange(
                createURLWithPort("/reward/12"), HttpMethod.GET, null, UserPoints.class);
        assertNotNull(rewardResponse);
        assertEquals(rewardResponse.getStatusCode().value(), 200);
        assertEquals(rewardResponse.getBody().getPoints().getPoints(), 3976);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
