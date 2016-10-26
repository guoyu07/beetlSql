sample
===
* 注释

	select #use("cols")# from user where #use("condition")#

cols
===

	id,name,role,createTime,updateTime,creator,reviser,loginName,loginPwd,isDeleted

updateSample
===

	`id`=#id#,`name`=#name#,`role`=#role#,`createTime`=#createtime#,`updateTime`=#updatetime#,`creator`=#creator#,`reviser`=#reviser#,`loginName`=#loginname#,`loginPwd`=#loginpwd#,`isDeleted`=#isdeleted#

condition
===

	1 = 1  
	@if(!isEmpty(name)){
	 and `name`=#name#
	@}
	@if(!isEmpty(role)){
	 and `role`=#role#
	@}
	@if(!isEmpty(createtime)){
	 and `createTime`=#createtime#
	@}
	@if(!isEmpty(updatetime)){
	 and `updateTime`=#updatetime#
	@}
	@if(!isEmpty(creator)){
	 and `creator`=#creator#
	@}
	@if(!isEmpty(reviser)){
	 and `reviser`=#reviser#
	@}
	@if(!isEmpty(loginname)){
	 and `loginName`=#loginname#
	@}
	@if(!isEmpty(loginpwd)){
	 and `loginPwd`=#loginpwd#
	@}
	@if(!isEmpty(isdeleted)){
	 and `isDeleted`=#isdeleted#
	@}
	
