app
assets bundle
storage bundle

ftp server
app_patches(dir)
	install_version(dir)
		updateInfo.js
		app_version_patches(dir)
			bundle_patch.txt

根据lastBundle生成patches
根据配置生成install_version的updateInfo

初始化时bundle放在assets

if app第一次启动
	把bundle复制到app的用于更新的存储目录
检查更新
if 需要更新
	下载bundle补丁，更新bundle
重进app
