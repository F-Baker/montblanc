package montblanc;

import montblanc.controller.AdminController;
import montblanc.controller.AuthController;
import montblanc.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SmokeTest {

    @Autowired
    AdminController adminController;
    @Autowired
    UserController userController;
    @Autowired
    private AuthController authController;

    @Test
    public void contextLoads() throws Exception {
        try {
            assertThat(authController).isNotNull();
            assertThat(adminController).isNotNull();
            assertThat(userController).isNotNull();
        } catch (Exception e) {
            System.out.println("One of the controllers didn't load properly");
        }
    }
}
