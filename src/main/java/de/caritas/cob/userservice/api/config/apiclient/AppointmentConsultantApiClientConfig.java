package de.caritas.cob.userservice.api.config.apiclient;

import de.caritas.cob.userservice.appointmentservice.generated.ApiClient;
import de.caritas.cob.userservice.appointmentservice.generated.web.ConsultantApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for the AppointmentService API client.
 */
@Configuration
public class AppointmentConsultantApiClientConfig {

  @Value("${appointment.service.api.url}")
  private String appointmentServiceApiUrl;

  /**
   * AppointmentService controller bean.
   *
   * @param appointmentServiceApiUrl {@link ApiClient}
   * @return the ConsultantApi controller {@link ConsultantApi}
   */
  @Bean
  public ConsultantApi consultantApi(ApiClient appointmentServiceApiUrl) {
    return new ConsultantApi(appointmentServiceApiUrl);
  }

  /**
   * AppointmentService API client bean.
   *
   * @param restTemplate {@link RestTemplate}
   * @return the AppointmentService {@link ApiClient}
   */
  @Bean
  @Primary
  public ApiClient appointmentConsultantApiClient(RestTemplate restTemplate) {
    return new ApiClient(restTemplate).setBasePath(this.appointmentServiceApiUrl);
  }

}
