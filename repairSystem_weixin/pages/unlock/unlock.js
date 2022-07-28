// pages/unlock/unlock.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    index: 0,
    companyList:[
      {
        companyName:"开得快开锁公司",
        companyTel:"1111111"
      },{
        companyName:"开得慢开锁公司",
        companyTel:"3333333"
      },{
        companyName:"开得开开锁公司",
        companyTel:"5555555"
      },{
        companyName:"开不开开锁公司",
        companyTel:"7777777"
      }
    ],
    // unlockList:[
    //   "门锁:80",
    //   "车锁:120"
    // ]/*改为连接数据库 */
  },
  changeTabbar(e){
    this.setData({ index: e.currentTarget.dataset.id})
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