// pages/complaintOne/complaintOne.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    repairmanId : wx.getStorageSync('orderRepairId'),
    complaint:"",
    complaintList:[],
    residentId:10000007,
    orderId:wx.getStorageSync('orderId'),

  },
  commit:function(){
    wx.request({
      url: 'http://localhost:9090/complaint/saven',
      method:"POST",
      header: {
        'content-type': 'application/json'
      },
      data:{
        orderId:this.data.orderId,
        complaintContent:this.data.complaint,
        residentId:10000007,
        repairmanId:this.data.repairmanId,
        complaintContent:this.data.complaint
      },
      success(res){
        console.log("投诉成功",res);
        wx.showToast({
          title: '投诉成功',
          icon: 'success',
          duration: 2000//持续的时间
        });
      },
      fail(res){
        console.log("投诉失败");
      }
    });
    this.setData({
      complaint:"",
    });
  },
  reset:function(){
    this.setData({
      complaint:"",
    });
  },
  /* 微信小程序不能双向绑定，所以要用bindinput设置函数，当输入时对要被设置的值赋值 */
  complaintText:function(e){
    this.setData({
      complaint:e.detail.value
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    
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