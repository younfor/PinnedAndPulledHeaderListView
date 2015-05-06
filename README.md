# PinnedAndPulledHeaderListView
仿QQ好友列表分组悬停，自定义Header，下拉刷新结合Demo

之前学习了Pulltorefresh，pinnedheaderexpanablelistview 但是结合起来还是有点麻烦的。尤其是像QQ这种。他不是单纯的第一个当做分组。他是分组上面还有几个按钮，还有搜索框，同时可以滑动，而且还可以悬停。想了试了好几种方法，都有BUG。最后用的一种方法。 

1. pulltorefresh用的android.v4里面自带的，好像知乎也是 

2. 悬停和分组用的网上的，然后我把第一个分组的样式改成了自定义的menu菜单，并且清空了child.这样看上去就像一个自定义layout和listview一起滑动。 

![image](http://img.blog.csdn.net/20150506131255781)
![image](http://img.blog.csdn.net/20150506131328122)
![image](http://img.blog.csdn.net/20150506131400559)