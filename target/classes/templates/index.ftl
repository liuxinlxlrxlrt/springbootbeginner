this is itmayiedu<br>
${name}
<#if sex=1>
    男
    <#elseif sex=0>
    女
    <#else>
    其他
</#if>

<#list userList as user>
    ${user}
</#list>