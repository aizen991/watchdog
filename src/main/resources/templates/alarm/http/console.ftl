
####################################################################################################
####                                             监控器信息                                                                                                                                          #####
####################################################################################################
ID:${monitor.id}		
类型:${monitor.type}		
名称:${monitor.name}	
链接地址:${monitor.url}
方法:${monitor.method}
请求参数:${monitor.requestParams!""}
用户:${monitor.userid!""}
密码:${monitor.password!""}
Agent:${monitor.userAgent!""}
间隔:${monitor.pollingInterval}
请求头:${monitor.httpHeader!""}
应该包含:${monitor.shouldContain!""}
不该包含:${monitor.shouldNotContain!""}
大小写敏感:${monitor.caseSensitive!""}
####################################################################################################
####                                             监控记录                                                                                                                                              #####
####################################################################################################
ID:${record.id}
响应时间:${record.responseTime}
状态码:${record.code}
返回内容:${record.body!""}
消息:${record.message!""}
<#include "/alarm/common/console_reports.ftl">
