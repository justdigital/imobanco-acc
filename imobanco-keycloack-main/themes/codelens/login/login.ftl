
<#import "template.ftl" as layout>

<#assign title = "Meu Título Personalizado">

<@layout.registrationLayout displayInfo=false displayMessage=false; section>
    <#if section = "title">
        ${title}
    <#elseif section = "form">
        <#if realm.password>
        <div id="app-container">
          <div id="imobanco-container" class="flex-center">
            <img src="https://raw.githubusercontent.com/RafaelLeitePag/images/main/logo.svg" />
          </div>
          <div id="login-container" class="flex-center">
            <form id="kc-form-login" class="${properties.kcFormClass!}" onsubmit="login.disabled = true; return true;" action="${url.loginAction?keep_after('^[^#]*?://.*?[^/]*', 'r')}" method="post">
              <div class="flex column">
                <h1>Autenticador</h1>
                  <#if usernameEditDisabled??>
                      <fieldset class="flex column">
                        <label>Nome de Usuário:</label>
                        <input tabindex="1" id="username" class="${properties.kcInputClass!}" name="username" value="${(login.username!'')}" type="text" disabled placeholder="<#if !realm.loginWithEmailAllowed>${msg("username")}<#elseif !realm.registrationEmailAsUsername>${msg("usernameOrEmail")}<#else>${msg("email")}</#if>"/>
                      </fieldset>
                      <#else>
                       <fieldset class="flex column">
                          <label>Nome de Usuário:</label>
                          <input tabindex="1" id="username" class="${properties.kcInputClass!}" name="username" value="${(login.username!'')}" type="text" autofocus autocomplete="off" placeholder="<#if !realm.loginWithEmailAllowed>${msg("username")}<#elseif !realm.registrationEmailAsUsername>${msg("usernameOrEmail")}<#else>${msg("email")}</#if>" />
                        </fieldset>
                  </#if>

                 <fieldset class="flex column">
                              <label>Senha:</label>
                              <input tabindex="2" id="password" class="${properties.kcInputClass!}" name="password" type="password" autocomplete="off" placeholder="${msg("password")}"/>
                  </fieldset>

              </div>
              <#if realm.resetPasswordAllowed>
                  <a href=""> Esqueceu sua senha? </a>
              </#if>

              <button type="submit" tabindex="4" name="login" id="kc-login">Entrar</button>

              <#if message?has_content>
                  <div id="login-alert" class="alert alert-danger col-sm-12">
                      <span class="kc-feedback-text">${kcSanitize(message.summary)?no_esc}</span>
                  </div>
              </#if>

            </form>
          </div>
        </div>
    </#if>
</#if>
</@layout.registrationLayout>