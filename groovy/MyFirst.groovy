public class HelloWorld {
 public static void main(args) {
  def message = "Hello World"
  println message
  // 循环
  def hw = new HelloWorld()
  hw.repeat2("hi world", 5)
  // 集合
  def range = 0..4
  println range
  println range.class
  // assert 命令用来证明 range 是 java.util.List 的实例
  assert range instanceof List
  
  def coll = ["Groovy", "Java", "Ruby"]
  println coll.class
  assert  coll instanceof Collection
  assert coll instanceof ArrayList
  // 添加项
  coll.add("Python")
  coll << "Smalltalk"
  coll[5] = "Perl"
  println coll
  
  // it 是 Groovy 的默认变量
  coll.each{
    println it
  }
  
  range.each{ value ->
   println value
  }
  
  // Map 类型
  def hash = [name:"Andy", "VPN-#":45]
  println hash.getClass()
  hash.dob = "01/29/76"
  println hash
  
  hash.each{ key, value ->
   println "${key} : ${value}"
  }

 }
 
 
 def repeat(val){
	for(i in 0..5){
	//for(i = 0; i < 5; i++){
		println val
	}
 }
 
 def repeat2(val, repeat=5){
  for(i in 0..<repeat){
   println val
  }
 }
}
