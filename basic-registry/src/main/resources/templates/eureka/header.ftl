<#import "/spring.ftl" as spring />
<nav class="navbar navbar-default" role="navigation">
  <div class="container">
    <div class="navbar-header">
      <img class="logo" src="eureka/images/spring-logo-eureka.png">
       <font  class = "logo_title">大禹智慧水务服务注册中心</font> 
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
        <li>
          <a href="<@spring.url dashboardPath/>">首页</a>
        </li>
        <li>
          <a href="<@spring.url dashboardPath/>/lastn">近1000条记录</a>
        </li>
      </ul>
    </div>
  </div>
</nav>


