// pages/mine/mine.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgPath: wx.getStorageSync('avatarUrl'),
    nickname: wx.getStorageSync('userInfo').nickName

  },
  toMessage:function(){
    wx.navigateTo({
      url: '/pages/message/message',
    })
  },
  toComplaint:function(){
    wx.navigateTo({
      url: '/pages/complaint/complaint',
    })
  },
  toHome:function(){
    wx.setStorageSync('roleId',0);
    wx.navigateTo({
      url: '/pages/logins/logins',
    });
  },
  // nick:function(){
  //   console.log(wx.getStorageSync('userInfo'));
  // },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    // var that = this;
    // that.imgPath = wx.getStorageSync('avatarUrl');
    // console.log(imgPath);
    //console.log(this.nickname);
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    if (typeof this.getTabBar === 'function' &&
      this.getTabBar()) {
      this.getTabBar().setData({
        selected: 0
      })
    }
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