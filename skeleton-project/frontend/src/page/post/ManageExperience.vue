<template>
    <div class="container marketing">

      <!-- Three columns of text below the carousel -->
      <!-- 모든 태그들 -->
      <div class="row box-table">
        <div class="col-lg-2"><!-- #없는거 빼기 -->
            <div>
                #No태그 여부 <b-img  @click="exeptNoTagClickE(isIncludeNoTag)" :pressed.sync="NoTagImgSrcT" style="cursor:pointer" v-bind:src="require(`@/assets/img/${NoTagImgSrcT}`)" width="20px"></b-img>
            </div>
        </div><!-- /.col-lg-2 -->
        <div class="col-lg-2"  v-for="tag in tags" :key="tag.tid">
            
            <div>
                #{{tag.tag_Name}}  <b-img @click="filtering(tag)" :pressed.sync="tag.state"  variant="primary" style="cursor:pointer" v-bind:src="require(`@/assets/img/${tag.imgsrc}`)" width="20px"></b-img>
            </div>
              
        </div><!-- /.col-lg-2 -->
      </div><!-- /.row -->



      <!-- 경험목록 -->
      <div class="row">
        <div v-for="(experience,exid) in experiences" :key="experience.exid">
        <div v-if="showProject(experience)">
          <div v-bind:style = "mystyle" v-on:mouseover = "changebgcolor" v-on:mouseout = "originalcolor" class="img-circle">
            <div class="content">
              <ul>
                <div>
                  <li v-for="experienceTag in experience.tags" :key="experienceTag.tid" style="display: inline-block">
                       #{{experienceTag.tagName}}
          
                   </li>
                   
                </div>
              </ul>
            </div>
          </div>
          <h2>{{experience.title}}</h2>
          <small>{{experience.startdate}} ~ {{experience.enddate}}</small>
          <div class="txt_line"><p>{{experience.contents}}</p></div>
          <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
        </div><!-- /.col-lg-4 -->
        </div>
      </div><!-- /.row -->

      <!-- (+) 버튼 -->
      <div class="row">
        <div class="col-lg-12">
            <div>
               <b-img v-on:click="addExp" :src="require(`@/assets/img/icons8-plus-50.png`)" width="60px" v-bind:style = "buttonStyle" v-on:mouseover = "change_button" v-on:mouseout = "origin_button"></b-img>
            </div>
        </div><!-- /.col-lg-4 -->
      </div><!-- /.row -->

      <!-- START THE FEATURETTES -->

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading"> 경험을 적어보세요~! <span class="text-muted">반드시 도움이 될거에요.</span></h2>
          <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
        </div>
        <div class="col-md-5">
          <img class="featurette-image img-responsive center-block" src="https://t1.daumcdn.net/cfile/tistory/21151B4E53E83DAF2C" alt="Generic placeholder image">
        </div>
      </div>

      <hr class="featurette-divider">

    
      
      <hr class="featurette-divider">
    </div>

</template>

<script>
import constants from "../../lib/constants.js";
import axios from "axios";

//const SERVER_URL = "http://localhost:8080";

export default {
  name: "ManageExperience",
  data: () => {
    return {
      tags: [],
      uid : localStorage["uid"],
      experiences: [],
      noTagExperiences: [],
      selectedTags: [],
      isIncludeNoTag  : true,
      NoTagImgSrcT: "icons8-doorbell-50.png",
      ex:[],
      experienceTags: {
        "": [],
      },
      mystyle: {
        width :"",
        height : "",
        opacity: ""
      },
      buttonStyle:{
        width :"",
        opacity: ""
      }
    };
  },
  beforeCreate () {
  
   axios
      .get(this.$SERVER_URL + `/experience/Tags`, {
        params: {
          uid: localStorage["uid"],
        },
      })
      .then((response) => {
        // alert(this.$SERVER_URL + `/experience/Tags`);
       console.log(response.data.object);
       //
       this.tags = response.data.object;
        Array.prototype.forEach.call(this.tags, t =>
        Object.assign(t, {imgsrc:"icons8-circled-x-16.png"}),
        )

        Array.prototype.forEach.call(this.tags, tag => 
          this.selectedTags.push(tag.tid)
        )
      
        
       console.log(this.tags);
       
      })
      .catch((error) => {
        console.log(error);
        //alert("실패");
      });

      axios
      .get(this.$SERVER_URL + `/experience/all`, {
        params: {
          uid: localStorage["uid"],
        },
      })
      .then((response) => {
        
        console.log(response);
        console.log(response.data.object);
        this.experiences = response.data.object;
        console.log(response.data.object[0].portfolioTags);

        //
        this.experiences = response.data.object;
        
      })
      .catch((error) => {
        console.log(error);
      });
   }
   ,
  created() {
    //alert(this.$SERVER_URL + `/portfolio/all`);
    if (!constants.IS_LOGED_IN) {
      this.$router.push({ name: constants.URL_TYPE.MAIN.NOLOGINHOME });
    }
    
  },
   methods: {
    changebgcolor: function() {
      this.mystyle.opacity = "0.7";
    },
    originalcolor: function() {
      this.mystyle.opacity = "0.6";
    },
    change_button: function(){
      this.buttonStyle.opacity = "1";
      this.buttonStyle.width = "61px";
    },
    origin_button: function(){
      this.buttonStyle.opacity = "0.6";
        this.buttonStyle.width = "60px";
    },

    exeptNoTagClickE: function(isIncludeNoTag){
      //alert("바뀔 값" + isIncludeNoTag);
       this.isIncludeNoTag = !this.isIncludeNoTag;

    },


    addOrDeleteTagClickE: function(taginput){
        //alert("선택 " +taginput.tid);
          if(!taginput.state){//불포함 상태
            taginput.imgsrc = "icons8-joyent-64.png";
           // this.tags.splice(taginput.tid,1);
          }
          else {//포함 상태
            taginput.imgsrc = "icons8-circled-x-16.png";
            //this.tags.push(taginput);
          }
          taginput.state = !taginput.state;
          //alert("값들" + this.isIncluedTag)
          console.log("값들" + this.isIncluedTag);
          console.log(taginput.tid +" - " +taginput.state + " - " + taginput.imgsrc);
    },

    showProject(experience) {
      var selectedTags = this.selectedTags
      // console.log(selectedTags)
      var cnt = 0
      experience.tags.forEach(function(tag) {
        if (selectedTags.includes(tag.tid)) {
          cnt ++;
        };
        return cnt;
      });
  
  
      if (cnt>0 || (experience.tags.length==0 && this.isIncludeNoTag)) {
        // console.log('yes!')
        return true
      } else {
        // console.log("no!")
        return false
      }
  
    },

  
    
    filtering(tag) {
       
      if (this.selectedTags.includes(tag.tid)) {
        this.selectedTags.splice(this.selectedTags.indexOf(tag.tid), 1)
         tag.imgsrc = "icons8-joyent-64.png";
      } else {
        this.selectedTags.push(tag.tid)
        tag.imgsrc = "icons8-circled-x-16.png";
      }
      tag.state = !tag.state;
     
    },


    addExp: function(){      
      var date = new Date();
      var year = date.getFullYear();
      var month = date.getMonth()+1;
      var day = date.getDate();

      if(day<10) day = "0"+day;
      if(month<10) month = "0"+month;
       
      var startdate = year+"-"+month+"-"+day;
      
      //alert(this.uid);
        axios
      .post(this.$SERVER_URL + `/experience`, {
        title:"제목",uid:this.uid, 
      })
      .then((response) => {
        //alert("성공");
        //alert(response.data.object.title);
        response.data.object.startdate = startdate;
        response.data.object.enddate = startdate;
        this.experiences.push(response.data.object);
          
      })
      .catch((error) => {
        //alert("실패");
        console.log(error); 
      });
    },

  },
};
</script>

<style>

.box-table{
   border: 1px solid #888888;
   box-shadow: 0 0 2px rgb(111, 111, 111);
  margin: 100px;
  padding: 2%;
  background: #eeeeee;
  opacity: 0.8;

}
.img-circle {
    position: relative;
    background:#000000;
    
    border-radius: 50%;
    height: 200px;
    width: 200px;
    opacity: 0.6;
        background-size: cover;
      margin-left: auto;

  margin-right: auto;
      
    border: 3px solid #222222;
    box-shadow: 0 0 8px rgb(111, 111, 111);
}

.img-circle .content{
      width: 65%;
      height: 65%;
         position: relative;
         transform: translate(25%, 30%);
         font-size:15px;
         color: white;
         margin-top: auto;
         margin-bottom: auto;
    }

    .txt_line p{
        overflow: hidden;
       text-overflow: ellipsis;
      width: 250px;

      white-space: normal;
      line-height: 1.2;
      max-height: 2.4em;

      margin-left: auto;
      margin-right: auto;
      text-align: center;
    }

</style>