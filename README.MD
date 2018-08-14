spring security demo

基于spring boot 2.0.4.RELEASE(spring boot 2依赖于 spring 5), jdk 1.8

mysql 
redis

參照：http://www.spring4all.com/article/428

遇到的问题：
1.spring 5不再使用 extends WebMvcConfigurerAdapter 来自定义viewController,spring boot2依赖于spring 5，改用implements WebMvcConfigurer.

2.需要增加passwordEncoder，否则会报There is no PasswordEncoder mapped for the id "null"
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
 3.在class WebSecuriryConfiguartion extends WebSecurityConfigurerAdapter中指定passwordEncoder   
        @Override
       protected void configure(AuthenticationManagerBuilder auth) throws Exception {
           auth.userDetailsService(mysqlUserDetailService).passwordEncoder(passwordEncoder);
       }
       
 并且在保存用户的service中加密用户输入的密码：userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
 保存到数据库后，会出现以下数据{bcrypt}$2a$10$DGa7U2U1We16/utGfiO4AusUxHQ/Jm4oadZADRH.LBZKAw06Jtjxu
 {bcrypt}为加密所用地方encoder的id，具体见PasswordEncoderFactories中定义。
 
    
  