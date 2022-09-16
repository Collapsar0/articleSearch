<template>
  <div class="main">
    <div class="title">
      <div class="title-text">
        上市公司年报主题分析系统
      </div>

      <div class="back" @click="logout">
        <i class="el-icon-s-home" >登出</i>
      </div>
    </div>
    <div class="searchBox">

      <div class="detailBox">
<!--        <div class="stockCode">-->
<!--          <div class="text">-->
<!--            版块：-->
<!--          </div>-->
<!--          <div class="box-input">-->
<!--            <el-select v-model="optionsValue" clearable placeholder="请选择版块">-->
<!--              <el-option-->
<!--                  v-for="item in options"-->
<!--                  :key="item.value"-->
<!--                  :label="item.label"-->
<!--                  :value="item.value">-->
<!--              </el-option>-->
<!--            </el-select>-->
<!--          </div>-->
<!--        </div>-->

        <div class="stockCode">
          <div class="text">
            来源：
          </div>
          <div class="box-input">
            <el-select v-model="sourceValue" clearable placeholder="请选择文件来源">
              <el-option
                  v-for="item in sourceOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
          </div>
        </div>

        <div class="stockCode">
          <div class="text">
            股票代码：
          </div>
          <div class="box-input">
            <el-input
                placeholder="请输入股票代码"
                v-model="stockCode"
                clearable>
            </el-input>
          </div>
        </div>

        <div class="stockCode">
          <div class="text">
            公司名称：
          </div>
          <div class="box-input-year">
            <el-input
                placeholder="请输入公司名称"
                v-model="companyName"
                clearable>
            </el-input>
          </div>
        </div>

        <div class="stockCode">
          <div class="text">
            年度：
          </div>
          <div class="block">
            <el-date-picker
                format="yyyy年"
                value-format="yyyy"
                v-model="startYear"
                type="year"
                placeholder="选择年">
            </el-date-picker>
          </div>

          <div class="text-2">
            至
          </div>
          <div class="block">
            <el-date-picker
                format="yyyy年"
                value-format="yyyy"
                v-model="endYear"
                type="year"
                placeholder="选择年">
            </el-date-picker>
          </div>
        </div>

        <div class="button-box">
          <el-button type="primary" @click="searchHandler" style="background-color: #24292f;border: white">搜索
          </el-button>

<!--          <download-excel-->
<!--              class="export-excel-wrapper"-->
<!--              :data   = "tableData"-->
<!--              :fields = "json_fields"-->
<!--              type    = "xls"-->
<!--              name    = "查询结果.xls">-->
<!--            -->
<!--          </download-excel>-->
          <el-button type="primary" @click="export2Excel" style="background-color: #24292f;border: white">导出表格</el-button>

        </div>
      </div>
      <div class="tag-box">
        <div class="tag-box-title">
          <div class="line-text-2">
            关键词：
          </div>
          <el-button type="primary" @click="dialogFormVisible = true" size="small" style="background-color: #24292f;border: white">主题与关键词管理</el-button>
        </div>
        <!--主题与Tag弹窗-->
        <el-dialog title="主题与关键词管理" :visible.sync="dialogFormVisible">
          <div class="theme-box">
            <div class="text">
              主题
            </div>
            <el-select v-model="themeValue" @change="getTagsByTheme" placeholder="请选择主题" style="margin-left: 3%">
              <el-option
                  v-for="item in themeOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
            <el-button type="primary" @click="deleteTheme" style="background-color: #24292f;border: white;margin-left: 1%">删除当前主题</el-button>
            <el-input
                style="width: 180px;margin-left: 3%"
                placeholder="请输入新主题"
                v-model="newThemeInput"
                clearable>
            </el-input>
            <el-button type="primary" @click="addNewTheme" style="background-color: #24292f;border: white;margin-left: 1%">添加主题</el-button>
          </div>
          <div class="text-tag">
            当前主题下的关键词
          </div>
          <el-tag
              :key="tag"
              v-for="tag in themeTags"
              closable
              :disable-transitions="false"
              @close="handleClose4Theme(tag)">
            {{ tag }}
          </el-tag>
          <el-input
              class="input-new-tag"
              v-if="inputVisible4Theme"
              v-model="inputValue4Theme"
              ref="saveTagInput4Theme"
              size="small"
              @keyup.enter.native="handleInputConfirm4Theme"
              @blur="handleInputConfirm4Theme"
          >
          </el-input>
          <el-button v-else class="button-new-tag" size="small" @click="showInput4Theme">+ New Tag</el-button>

          <!--弹窗下部的按钮-->
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogFormVisible = false" style="background-color: #24292f;border: white">确 定</el-button>
          </div>
        </el-dialog>

        <!--动态输入tag-->
        <el-tag
            :key="tag"
            v-for="tag in dynamicTags"
            closable
            :disable-transitions="false"
            @close="handleClose(tag)">
          {{ tag }}
        </el-tag>
<!--        <el-cascader-->
<!--            style="width: 100px"-->
<!--            :options="options4Tags"-->
<!--            :show-all-levels="false"-->
<!--            v-if="inputVisible"-->
<!--            v-model="inputValue"-->
<!--            ref="saveTagInput"-->
<!--            size="small"-->
<!--            @change="handleInputConfirm"-->
<!--            :props="{ expandTrigger: 'hover' }"-->
<!--        >-->
<!--        </el-cascader>-->

        <el-select v-model="inputValue"
                   placeholder="请选择主题"
                   style="width: 120px"
                   v-if="inputVisible"
                   ref="saveTagInput"
                   size="small"
                   @change="handleInputConfirm">
          <el-option
              v-for="item in options4Tags"
              :key="item.label"
              :label="item.label"
              :value="item.label">
          </el-option>
        </el-select>

        <el-button v-else class="button-new-tag" size="small" @click="showInput">+ 请选择主题</el-button>
      </div>

    </div>
    <!--搜索结果展示-->
    <div class="result">
      <el-table
          :data="tableData"
          border
          stripe
          style="width: 100%">
<!--        <el-table-column-->
<!--            fixed-->
<!--            prop="id"-->
<!--            label="ID"-->
<!--            width="100">-->
<!--        </el-table-column>-->
        <el-table-column
            fixed
            prop="companyName"
            label="公司名称"
            width="120">
        </el-table-column>

<!--        <el-table-column-->
<!--            prop="section"-->
<!--            label="版块"-->
<!--            width="250">-->
<!--        </el-table-column>-->

        <el-table-column
            prop="stockCode"
            label="股票代码"
            width="80">
        </el-table-column>

        <el-table-column
            prop="year"
            label="年度"
            width="80">
        </el-table-column>
        <el-table-column
            prop="pageNumber"
            label="总页数"
            width="80">
        </el-table-column>
        <el-table-column
            prop="fileSource"
            label="文件来源"
            width="150">
        </el-table-column>
<!--        <el-table-column-->
<!--            prop="publicDate"-->
<!--            label="报告时间"-->
<!--            width="120">-->
<!--        </el-table-column>-->
        <el-table-column
            prop="totalTags"
            label="关键词总出现次数"
            width="80">
        </el-table-column>
<!--        <el-table-column-->
<!--            prop="fileName"-->
<!--            label="文件名称"-->
<!--            width="200">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="tag1"-->
<!--            label="关键词1"-->
<!--            width="120">-->
<!--        </el-table-column>-->
        <el-table-column v-for="(item,index) in dynamicTags" :key="index"
            :prop="item"
            :label="item"
            width="120">
        </el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="100">
          <template slot-scope="scope">
            <el-tooltip class="item" effect="dark" :content="scope.row.fileName" placement="top">
              <el-button @click="handleClick(scope.row)" type="text" size="small">查看文件</el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!--分页器-->
    <div class="pageSelect">
      <div class="companyCount">
        共查询到{{reportCount}}条结果
      </div>
      <el-pagination
          background
          layout=" prev, pager, next"
          @current-change="pageHandler"
          :page-count="pageCount"
          :current-page="currentPage">
      </el-pagination>
    </div>


  </div>
</template>

<script>
import qs from "qs";
import global_ from '../Global.vue'


export default {
  name: "MainPage",
  created() {
    this.searchHandler()
    this.getSectionList()
    this.getThemeList()
    this.updateThemeAndTags()
  },
  data() {
    return {
      // 搜索条件
      // 版块选择
      options:
          [
            // {
            //   value: '选项1',
            //   label: '黄金糕'
            // }
          ],
      optionsValue: '',
      // 文件来源选择
      sourceOptions:[
        {
          value: '年报',
          label: '年报'
        },
        {
          value: '社会责任报告',
          label: '社会责任报告'
        }
      ],
      sourceValue: '',
      // 股票代码
      stockCode: '',
      companyName: '',
      // 年份范围搜索
      startYear: null,
      endYear: null,
      // 分页操作
      currentPage: 1,
      pageCount: 1,
      reportCount: 0,
      // 管理主题与Tag
      dialogFormVisible: false,
      newThemeInput: '',
      themeOptions: [
        // {
        //   value: '选项1',
        //   label: '黄金糕'
        // }
      ],
      themeValue: '',
      themeTags: [
          // "标签1","标签2"
      ],

      // 添加Tag
      dynamicTags: [
          // "碳排放","碳中和"
      ],
      inputVisible: false,
      inputVisible4Theme: false,
      inputValue: '',
      inputValue4Theme: '',
      // 可选择的Tag
      options4Tags: [
        // {
        //   value: 'zhinan',
        //   label: '指南',
        //   children: [{
        //     value: 'shejiyuanze',
        //     label: '设计原则',
        //     children: [{
        //       value: 'yizhi',
        //       label: '一致'
        //     }]
        //   }]
        // }
      ],
      // 表格内容
      tableData: [
      //     {
      //   id: '1',
      //   stockCode: '000059',
      //   companyName: '华锦股份',
      //   year: '2021',
      //   fileName: '宝泰隆新材料股份有限公司2020年年度报告',
      //   tag0: 12
      // },
      ],

      // 导出Excel表头数据
      json_fields: {
        "公司名称": 'companyName',
        "版块": 'section',
        "股票代码": 'stockCode',
        "年度": 'year',
        "报告时间": 'publicDate',
        "关键词总出现次数": 'totalTags',
      },
      json_meta: [
        [
          {
            key: "charset",
            value: "utf-8",
          },
        ]
      ],
    }
  },
  methods: {
    handleClose(tag) {
      this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
    },
    handleClose4Theme(tag) {
      this.themeTags.splice(this.themeTags.indexOf(tag), 1);
      this.updateTagsByTheme()
      this.updateThemeAndTags()
    },

    showInput() {
      this.inputVisible = true;
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },

    showInput4Theme() {
      if(this.themeValue===''){
        this.$message('请先选择需要添加关键词的主题');
      } else {
        this.inputVisible4Theme = true;
        this.$nextTick(_ => {
          this.$refs.saveTagInput4Theme.$refs.input.focus();
        });
      }

    },

    handleInputConfirm() {
      // let inputValue = this.inputValue[this.inputValue.length-1];
      // console.log(this.inputValue[this.inputValue.length-1])
      // if (inputValue) {
      //   this.dynamicTags.push(inputValue);
      // }
      // this.inputVisible = false;
      // this.inputValue = '';

      //  修改后 点击主题添加所有主题下的Tag
      const params = {
        themeName: this.inputValue,
      }
      if (this.inputValue) {
        this.$axios.get('/themeAndTag/getTagsList?' + qs.stringify(params)).then(res => {
          const gotData = res.data.data.tags
          for (const tag of gotData) {
            this.dynamicTags.push(tag["tagName"])
          }
        })
      }

      this.inputVisible = false;
      this.inputValue = '';


    },

    handleInputConfirm4Theme() {
      let inputValue = this.inputValue4Theme;
      if (inputValue) {
        this.themeTags.push(inputValue);
      }
      this.inputVisible4Theme = false;
      this.inputValue4Theme = '';
      this.updateTagsByTheme()
      this.updateThemeAndTags()
    },

    // 打开PDF文件
    handleClick(row) {
      console.log(row);
      console.log("打开PDF")
      if(row.fileSource==="年报"){
        // if(row.year==="2019"){
        //   window.open(global_.BASE_URL+"/ReportSrc/AR/"+row.year+"/"+row.section+"/"+row.fileName+".pdf",'_blank');
        // }else if(row.year==="2021"){
        //   window.open(global_.BASE_URL+"/ReportSrc/AR/"+row.year+"/"+row.section+"/"+row.fileName,'_blank');
        // } else{
        //   window.open(global_.BASE_URL+"/ReportSrc/AR/"+row.year+"/"+row.section+"/"+row.fileName+".PDF",'_blank');
        // }
        window.open(global_.BASE_URL+"/ReportSrc/AR/"+row.year+"/"+row.section+"/"+row.fileName,'_blank');
      }
      else if(row.fileSource==="社会责任报告"){
        // if(row.year==="2021"){
        //   window.open(global_.BASE_URL+"/ReportSrc/ESG/"+row.year+"/"+row.fileName,'_blank');
        // }else {
        //   window.open(global_.BASE_URL+"/ReportSrc/ESG/"+row.year+"/"+row.fileName+".PDF",'_blank');
        // }
        window.open(global_.BASE_URL+"/ReportSrc/ESG/"+row.year+"/"+row.fileName,'_blank');
      }

    },
    // 发送筛选请求
    searchHandler() {
      this.currentPage = 1
      let contentString = ""
      for (const index of this.dynamicTags) {
        contentString += index+','
      }

      const params = {
        section: this.optionsValue,
        stockCode: this.stockCode,
        companyName: this.companyName,
        year: '',
        fileName: this.fileName,
        content: contentString,
        page: this.currentPage,
        fileSource: this.sourceValue,
      }
      if(this.startYear===null&&this.endYear===null){
        params.year = '0-9999'
      } else if(this.startYear===null&&this.endYear!==null){
        params.year = '0-'+this.endYear
      }else if(this.startYear!==null&&this.endYear===null){
        params.year = this.startYear+'-9999'
      } else {
        params.year = this.startYear+'-'+this.endYear
      }
      // console.log(params.year)

      this.$axios.get('/reports/getReportList?' + qs.stringify(params)).then(res => {

        const gotData = res.data.data.reports.reportList
        this.tableData = gotData


        // 命中次数绑定
        for(let i=0;i<gotData.length;i++){
          // console.log(gotData[i].content.replace("{","").replace("}",""))
          let contentArry = gotData[i].content.replace("{","").replace("}","").split(", ")
          // console.log(contentArry)
          this.tableData[i]["totalTags"]=0
          for (const argument of contentArry) {
            let tempArry = argument.split("=")
            this.tableData[i][tempArry[0]]=tempArry[1]
            this.tableData[i]["totalTags"] += parseInt(tempArry[1])
          }
        }

        this.pageCount = res.data.data.reports.pageCount
        this.reportCount = res.data.data.reports.reportCount
      })
    },
    // 下载请求
    export2Excel() {
      this.$message({
        message: '表格正在导出中，请稍等，等待时间随数据大小而定',
        type: 'warning'
      });
      require.ensure([], () => {
        this.currentPage = 1
        let contentString = ""
        for (const index of this.dynamicTags) {
          contentString += index+','
        }

        const params = {
          section: this.optionsValue,
          stockCode: this.stockCode,
          companyName: this.companyName,
          year: '',
          fileName: this.fileName,
          content: contentString,
          page: -31901224,
          fileSource: this.sourceValue,
        }
        if(this.startYear===null&&this.endYear===null){
          params.year = '0-9999'
        } else if(this.startYear===null&&this.endYear!==null){
          params.year = '0-'+this.endYear
        }else if(this.startYear!==null&&this.endYear===null){
          params.year = this.startYear+'-9999'
        } else {
          params.year = this.startYear+'-'+this.endYear
        }

        this.$axios.get('/reports/getReportList?' + qs.stringify(params),{timeout:1000*60*3}).then(res => {


          const gotData = res.data.data.reports.reportList
          let downloadTable = gotData

          // 命中次数绑定
          for(let i=0;i<gotData.length;i++){
            let contentArry = gotData[i].content.replace("{","").replace("}","").split(", ")
            downloadTable[i]["totalTags"]=0
            for (const argument of contentArry) {
              let tempArry = argument.split("=")
              downloadTable[i][tempArry[0]]=tempArry[1]
              downloadTable[i]["totalTags"] += parseInt(tempArry[1])
            }
          }

          // 导出Excel处理
          const { export_json_to_excel } = require('../vendor/Export2Excel');
          const tHeader = ['公司名称','股票代码', '年度', '总页数', '文件来源', '关键词总出现次数'];
          const filterVal = ['companyName','stockCode', 'year', 'pageNumber', 'fileSource','totalTags'];
          for (const tag of this.dynamicTags) {
            tHeader.push(tag)
            filterVal.push(tag)
          }
          const list = downloadTable;
          const data = this.formatJson(filterVal, list);
          export_json_to_excel(tHeader, data, '查询结果');

          this.$message({
            message: '表格导出成功',
            type: 'success'
          });
        })

      })
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => v[j]))
    },


    // 翻页请求
    pageHandler: function (currentPage) {
      this.currentPage = currentPage
      let contentString = ""
      for (const index of this.dynamicTags) {
        contentString += index+','
      }

      const params = {
        section: this.optionsValue,
        stockCode: this.stockCode,
        companyName: this.companyName,
        year: '',
        fileName: this.fileName,
        content: contentString,
        page: this.currentPage,
        fileSource: this.sourceValue,
      }
      if(this.startYear===null&&this.endYear===null){
        params.year = '0-9999'
      } else if(this.startYear===null&&this.endYear!==null){
        params.year = '0-'+this.endYear
      }else if(this.startYear!==null&&this.endYear===null){
        params.year = this.startYear+'-9999'
      } else {
        params.year = this.startYear+'-'+this.endYear
      }
      // console.log(params.year)
      this.$axios.get('/reports/getReportList?' + qs.stringify(params)).then(res => {

        const gotData = res.data.data.reports.reportList
        this.tableData = gotData
        // 命中次数绑定
        for(let i=0;i<gotData.length;i++){
          // console.log(gotData[i].content.replace("{","").replace("}",""))
          let contentArry = gotData[i].content.replace("{","").replace("}","").split(", ")
          // console.log(contentArry)
          this.tableData[i]["totalTags"]=0
          for (const argument of contentArry) {
            let tempArry = argument.split("=")
            this.tableData[i][tempArry[0]]=tempArry[1]
            this.tableData[i]["totalTags"] += parseInt(tempArry[1])
          }
        }
        this.pageCount = res.data.data.reports.pageCount
        this.reportCount = res.data.data.reports.reportCount
      })
    },
    // 登出
    logout() {
      this.$axios.post("/logout").then(res => {
        console.log(res)
        localStorage.clear()
        sessionStorage.clear()
        this.$router.push("/login")
      })
    },
    // 发送查询版块列表请求
    getSectionList() {

      this.$axios.get('/sections/getSectionList').then(res => {

        const gotData = res.data.data.sections
        for (const section of gotData) {
          this.options.push({
            value: section["sectionName"],
            label: section["sectionName"]
          })
        }

      })
      console.log(this.options)
    },
    // 查询主题列表请求
    getThemeList() {
      this.themeOptions= []
      this.$axios.get('/themeAndTag/getThemeList').then(res => {
        const gotData = res.data.data.themes
        for (const theme of gotData) {
          this.themeOptions.push({
            value: theme["themeName"],
            label: theme["themeName"]
          })
        }
      })

    },

    // 更新级联选择器
    updateThemeAndTags(){
      this.$axios.get('/themeAndTag/getThemeList').then(res => {
        const gotData = res.data.data.themes
        for (const theme of gotData) {

          // 更新Tag级联选择器
          this.dynamicTags = []
          this.options4Tags = []
          const params = {
            themeName: theme["themeName"],
          }
          this.$axios.get('/themeAndTag/getTagsList?' + qs.stringify(params)).then(res => {
            let themeChildren = []
            const gotData = res.data.data.tags
            for (const tag of gotData) {
              themeChildren.push({
                    value: tag["tagName"],
                    label: tag["tagName"],
                  }
              )
            }
            this.options4Tags.push({
              value: theme["themeName"],
              label: theme["themeName"],
              children:themeChildren
            })
          })
        }
      })
    },

    // 添加新主题
    addNewTheme() {
      if(this.newThemeInput ===''){
        this.$message('输入主题不能为空');
      } else {
        const params = {
          newTheme: this.newThemeInput,
        }

        this.$axios.get('/themeAndTag/addTheme?' + qs.stringify(params)).then(res => {
          this.getThemeList()
          this.updateThemeAndTags()
          this.newThemeInput = ''
          this.$message({
            message: '主题添加成功',
            type: 'success'
          });
        })
      }
    },
    // 删除主题
    deleteTheme() {
      if(this.themeValue ===''){
        this.$message('请选择要删除的主题');
      } else {
        const params = {
          deleteTheme: this.themeValue,
        }
        this.$axios.get('/themeAndTag/deleteTheme?' + qs.stringify(params)).then(res => {
          this.getThemeList()
          this.updateThemeAndTags()
          this.themeValue=''
          this.themeTags=[]
          this.$message({
            message: '主题删除成功',
            type: 'success'
          });
        })
      }
    },
    // 获取当前主题下的Tags
    getTagsByTheme() {
      const params = {
        themeName: this.themeValue,
      }
      this.$axios.get('/themeAndTag/getTagsList?' + qs.stringify(params)).then(res => {
        this.themeTags = []
        const gotData = res.data.data.tags
        for (const tag of gotData) {
          this.themeTags.push(
              tag["tagName"]
          )
        }
      })
    },
    // 更新主题下的Tags
    updateTagsByTheme() {
      let newTags=''
      for (let i=0;i<this.themeTags.length;i++){
        if(i===0){
          newTags += this.themeTags[0]
        } else {
          newTags += ","+this.themeTags[i]
        }
      }
      const params = {
        tags: newTags,
        themeName: this.themeValue,
      }
      this.$axios.get('/themeAndTag/updateTags?' + qs.stringify(params)).then(res => {
        // this.getTagsByTheme()
      })
    },
  }
}
</script>

<style scoped>
  .main{
    /*background-color: #31cef9;*/
    width: 100%;
    height: 100vh;
    align-items: center;
    overflow: auto;

    background: url("../assets/img/backgroud5.png") center center;
    background-size: cover;
    overflow: auto;
  }
  .title{
    width: 100%;
    height: 5%;
    color: #ffffff;
    /*background-color: #606266;*/
    background-color: #24292f;
    font-size: 20px;
    font-weight: bolder;
    margin: auto;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .title-text{
    width: 90%;
    padding-left: 42%;
  }
  .back{
    width: 10%;
    cursor: pointer;
  }
  .searchBox{
    width: 100%;
    height: 20%;
    padding-top:10px;

    /*background: url("../assets/img/backgroud5.png") center center;*/
    /*background-size: cover;*/
    /*overflow: auto;*/
  }
  .line-text{
    font-size: 25px;
    font-weight: bolder;
  }
  /*条件部分*/
  .detailBox {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
  }
  .stockCode{
    display: flex;
    flex-direction: row;
    align-items: center;
  }
  .text{
    font-size: 18px;
  }
  .text-2{
    font-size: 18px;
    margin-left: 10px;
    margin-right: 10px;
  }
  .box-input{
    width: 148px;
  }
  .box-input-year{
    width: 150px;
  }
  .el-date-editor.el-input, .el-date-editor.el-input__inner{
    width: 110px;
  }
  /*tag部分*/
  .tag-box{
    width: 90%;
    margin: 1% auto;
  }
  .tag-box-title {
    display: flex;
    flex-direction: row;
    align-items: center;
    margin-bottom: 1%;
  }
  .line-text-2{
    font-size: 18px;
  }
  /*动态Tag*/
  .el-tag + .el-tag {
    margin-left: 10px;
  }
  .button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }
  .input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }
  .result{
    width: 90%;
    margin: 2% auto;
    padding-bottom: 30px;
  }
  .pageSelect {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-bottom: 30px;
  }
  /*主题弹窗*/
  .theme-box {
    display: flex;
    flex-direction: row;
    align-items: center;
  }
  .text-tag {
    font-size: 14px;
    margin-top: 2%;
    margin-bottom: 1%;
  }
  ::v-deep .el-pagination.is-background .el-pager li:not(.disabled).active {
    background-color:#24292f;
    color: #fff;
  }
</style>
