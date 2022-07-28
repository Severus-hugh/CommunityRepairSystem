// pages/holiday/holiday.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    holidayList:[],
    flag:true,
    
  },
  load:function(){
    var that = this;
    wx.request({
      url: 'http://localhost:9090/holiday/find',
      method:'GET',
      header: {
        'content-type': 'application/json'
      },
      data:{
        // repaiamanId:81030001,
      },
      success(res){
        that.setData({
          holidayList : res.data,
        });
        console.log("获取请假记录成功",res);
      },
      fail(res){
        console.log("请假记录获取失败",res);
      }
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.load();
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})