package br.edu.ifto.aula02;

import br.edu.ifto.aula02.model.entity.UsuarioDetailsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration //classe de configuração
@EnableWebSecurity //indica ao Spring que serão definidas configurações personalizadas de segurança
public class SecurityConfiguration {
    @Autowired
    UsuarioDetailsConfig usuarioDetailsConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        customizer ->
                                customizer
                                        .requestMatchers("/pessoafisica/form").permitAll()
                                        .requestMatchers("/pessoajuridica/form").permitAll()
                                        .requestMatchers("/pessoafisica/save").permitAll()
                                        .requestMatchers("/pessoajuridica/save").permitAll()
                                        .requestMatchers("/produto/listar").permitAll()
                                        .requestMatchers("carrinho/adicionar").permitAll()
                                        .requestMatchers("carrinho/atualizar").permitAll()
                                        .requestMatchers("carrinho/remover").permitAll()
                                        .requestMatchers("carrinho/listar").permitAll()
                                        .requestMatchers("/imagens/**", "/css/**", "/js/**").permitAll()
                                        .requestMatchers("/pessoafisica/list").hasAnyRole("ADMIN", "user")
                                        .requestMatchers("/pessoajuridica/list").hasAnyRole("ADMIN", "user")
                                        .requestMatchers("/usuario/remove").hasAnyRole("ADMIN")
                                        .requestMatchers("/pessoa/remove").hasAnyRole("ADMIN", "user")
                                        .requestMatchers(HttpMethod.POST,"/usuario/save").permitAll()
                                        .requestMatchers("/vendas/**").authenticated()
                                        .anyRequest() //define que a configuração é válida para qualquer requisição.
                                        .authenticated() //define que o usuário precisa estar autenticado.
                )
                .formLogin(customizer ->
                        customizer
                                .loginPage("/login") //passamos como parâmetro a URL para acesso à página de login que criamos
                                .defaultSuccessUrl("/produto/listar", true)
                                .permitAll() //define que essa página pode ser acessada por todos, independentemente do usuário estar autenticado ou não.
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .httpBasic(withDefaults()) //configura a autenticação básica (usuário e senha)
                .logout(LogoutConfigurer::permitAll) //configura a funcionalidade de logout no Spring Security.
                .rememberMe(withDefaults()); //permite que os usuários permaneçam autenticados mesmo após o fechamento do navegador
        return http.build();
    }
    /**
     * Com o método, instanciamos uma instância do encoder BCrypt e deixando o controle dessa instância como responsabilidade do Spring.
     * Agora, sempre que o Spring Security necessitar condificar um senha, ele já terá o que precisa configurado.
     * @return
     */
    @Autowired
    public void configureUserDetails(final AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(usuarioDetailsConfig).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * Com o método, instanciamos uma instância do encoder BCrypt e deixando o controle dessa instância como responsabilidade do Spring.
     * Agora, sempre que o Spring Security necessitar condificar um senha, ele já terá o que precisa configurado.
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}