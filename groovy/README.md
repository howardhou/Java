# Groovy

- Groovy 教程： https://www.w3cschool.cn/groovy/
  - Groovy是一种基于JVM（Java虚拟机）的敏捷开发语言，它结合了Python、Ruby和Smalltalk的许多强大的特性，Groovy 代码能够与 Java 代码很好地结合 
  - 官方网站： http://www.groovy-lang.org/

- [安装](http://www.groovy-lang.org/download.html): 下载Windows版，安装成功后设置环境变量：`C:\Program Files (x86)\Groovy\Groovy-2.5.5\bin`

- [精通 Groovy](https://www.ibm.com/developerworks/cn/education/java/j-groovy/j-groovy.html) : 这篇文章写得比较好 
    - 用 Java 编写的 Hello World  
		- 先编译： javac HelloWorld.java
		- 再运行： java HelloWorld
    - 用 Groovy 编写的 Hello World  
		- groovy 文件不需要编译，它是在运行时编译的，可以直接运行： groovy MyFirst.groovy
		- 但是仍然可以进行编译， 使用命令： groovyc MyFirst.groovy
    - Groovy 会根据对象的值来判断它的类型： def value = "Hello World"

- 在 Java 应用程序中加一些 Groovy 进来 : https://www.ibm.com/developerworks/cn/java/j-pg05245/

- [groovy与java语法区别](https://www.cnblogs.com/laodaodao/p/10102072.html)

- hello.groovy
	- 使用 Spring Boot CLI 命令运行 : `spring run hello.groovy`