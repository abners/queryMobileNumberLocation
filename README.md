# queryMobileNumberLocation

一个可以批量获取指定手机号码归属地的小程序，使用Java swing开发，可以读取excel文档中手机号，然后将获取到手机号归属地写入文档相应手机号的后一列

启动步骤：
目前为纯Java实现，改天改成Maven，因为使用到一些第三方的依赖包，像POI、http包之类的
![第三方依赖包](https://github.com/abners/queryMobileNumberLocation/blob/HEAD/touri/1.png)

1、运行源码包中的Launch类，会出现如下的窗口：
![第三方依赖包](https://github.com/abners/queryMobileNumberLocation/blob/HEAD/touri/2.png)

2.点击浏览，然后选择存有手机号的excel文档，最后点击获取手机号归属地按钮，即可

## 注意excel文档目前只支持手机号在文档第二列，然后程序会将归属地写入到后面一列：
![第三方依赖包](https://github.com/abners/queryMobileNumberLocation/blob/HEAD/touri/3.png)
