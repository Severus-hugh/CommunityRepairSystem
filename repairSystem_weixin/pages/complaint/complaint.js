// pages/complaint/complaint.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    index: 0,
    ordersList:[],
    orderIndex:0,
    complaintList:[],
    orderId:0,


  },
  load(){
    var that = this;
    wx.request({
      url: 'http://localhost:9090/orders/findcommited',
      method:"GET",
      header: {
        'content-type': 'application/json'
      },
      data:{
        currentStatus:4,/* 只查询已经结束的订单 */
        residentId:10000007,
      },
      success(res){
        that.setData({
          ordersList : res.data,
        });
        console.log(res);
      },fail(res){
        console.log("获取通知列表失败");
      }
    });
    wx.request({
      url: 'http://localhost:9090/complaint/findend',
      method:"GET",
      header: {
        'content-type': 'application/json'
      },
      data:{
        residentId : 10000007,
      },
      success(res){
        that.setData({
          complaintList : res.data,
        });
        console.log(res);
      },fail(res){
        console.log("获取投诉回复失败");
      }
    })

  },

  changeTabbar(e){
    this.setData({ index: e.currentTarget.dataset.id})
    this.load();
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.load();
  },
  commitComp:function(e){
    var that = this;
    console.log(e);
    var i = e.currentTarget.dataset.id;
    this.setData({
      orderIndex : that.data.ordersList[i].repairmanId,
      orderId : that.data.ordersList[i].orderId
    });//数据未定义要加this.data.
    console.log(this.data.orderIndex);
    wx.setStorageSync('orderRepairId', this.data.orderIndex);
    wx.setStorageSync('orderId', this.data.orderId);
    wx.navigateTo({
      url: '/pages/complaintOne/complaintOne',
    });
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