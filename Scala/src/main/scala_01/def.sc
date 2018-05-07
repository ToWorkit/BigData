def myFactor(x:Int):Int = {
  if (x <= 1)
    1
  else
    x * myFactor(x-1)
}
myFactor(5)