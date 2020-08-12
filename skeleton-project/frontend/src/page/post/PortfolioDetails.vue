<template>
  <div>
    <h2 v-if="!updateTitleState" class="title" style="color: grey;" @click="updateTitle">
      {{pjtDetail.title}}
      <!-- {{router.params.pid}} -->
    </h2>
    <h2 v-else class="title" style="color: grey;">
      <input size=70% :value="pjtDetail.title" @input="pjtDetail.title = $event.target.value" @keyup.enter="sendUpdateInfo(); updateTitle()">
      <b-img :src="require(`@/assets/img/icons8-save-close-64.png`)" width="15px" style="display: inline-block; margin-left: 1rem; cursor: pointer;" @click="sendUpdateInfo(); updateTitle()"></b-img>
      <!-- {{router.params.pid}} -->
    </h2>
    <b-button size="sm" variant="outline-dark">
      <b-icon-box-arrow-down class="mr-1"></b-icon-box-arrow-down>전체 파일 다운로드
    </b-button>
  
    
    <div class="tabs">
      <!-- <b-tabs content-class align="center"> -->
      <b-tabs content-class fill>
        <b-tab title="프로젝트 정보" active>
          <b-container class="tabContents">
            <b-row>

              <b-col v-if="!updateDateState" class="mt-4 mb-2">
                <p @click="updateDate()">프로젝트 진행 기간 : {{pjtDetail.start_date}} ~ {{pjtDetail.end_date}}</p>
              </b-col>
              
              <b-col v-else class="mt-4 mb-2">
                <p style="display: inline-block;">프로젝트 진행 기간 : <input :value="pjtDetail.start_date" @input="pjtDetail.start_date = $event.target.value" @keyup.enter="sendUpdateInfo(); updateDate()"> ~ <input :value="pjtDetail.end_date" @input="pjtDetail.end_date = $event.target.value" @keyup.enter="sendUpdateInfo(); updateDate()"></p>
                <b-img :src="require(`@/assets/img/icons8-save-close-64.png`)" width="15px" style="display: inline-block; margin-left: 1rem; cursor: pointer;" @click="sendUpdateInfo(); updateDate()"></b-img>
              </b-col>

            </b-row>
            <b-row>
              <b-col class="mt-2 mb-2">
                <h5>
                  <div v-if="pjtDetail.tag.length > 0">
                    <!-- 태그 -->
                    <!-- <div v-if="updateP">
                      <div v-for="(experienceTag,tid) in experience.tags" :key="experienceTag.tid">
                        <span class="txt_tag">
                          <span>#</span>
                          <span>{{experienceTag.tagName}}</span>
                          <b-img
                            @click="deleteTag(experience.tags, experienceTag ,tid, experience)"
                            style="width:18px; height:18px; cursor:pointer"
                            v-bind:src="require(`@/assets/img/icons8-close-window-50.png`)"
                          >
                            <span>삭제</span>
                          </b-img>
                        </span>
                      </div>

                      <span class="inp_tag">
                        <span>#</span>
                        <div style="inline-block" value="태그" placeholder="Tag입력">
                          <input
                            id="tagText"
                            v-model="tagText"
                            v-on:keyup.enter="addTag(experience.tags, experience.exid,tagText)"
                            type="text"
                            class="tf_g"
                            placeholder="태그입력"
                            style="box-sizing: content-box; width: 50px;"
                          />
                        </div>
                      </span>
                    </div>-->
                    <b-badge
                      v-for="(ptag, j) in pjtDetail.tag"
                      :key="j"
                      pill
                      class="mr-3"
                      id="tag"
                      text-variant="black"
                    >{{ ptag.tagName }}
                    <b-img :src="require(`@/assets/img/icons8-trash-24.png`)" width="15px" style="margin-left: 5%; cursor: pointer;" @click="deleteTag(ptag.tid)"></b-img>
                    </b-badge>

                      <input size=5 placeholder="태그 추가하기" v-model="newTag" @keyup.enter="addNewTag(newTag)">

                  </div>
                  <div v-else>
                    <b-icon-plus-square class="mr-1"></b-icon-plus-square>
                    <small>태그를 추가해보세요.</small>
                  </div>
                </h5>
              </b-col>
            </b-row>
            <b-row>
              <b-col class="mt-2 mb-2">
                <h5>프로젝트 정의</h5>

                <p v-if="!updateContentsState" @click="updateContents">{{ pjtDetail.contents }}</p>
                <p v-else>
                  <input class="input-contents" :value="pjtDetail.contents" @input="pjtDetail.contents = $event.target.value">
                  <b-img :src="require(`@/assets/img/icons8-save-close-64.png`)" width="15px" style="display: inline-block; margin-left: 1rem; cursor: pointer;" @click="sendUpdateInfo(); updateContents()"></b-img>
                </p>

              </b-col>
            </b-row>
          </b-container>
          <div></div>
        </b-tab>
        <b-tab title=" 파일 목록 ">
          <div class="tabContents">
            <b-container class="tabContents">
              <div v-if="pjtDetail.files.length>0">
                <b-row
                  v-for="(id, m) in pjtDetail.files"
                  :key="m"
                  class="mr-3"
                  id="file"
                  text-variant="black"
                >
                  <b-col class="mt-4 mb-2">
                    {{ id.fileName }}
                    <b-button size="sm" variant="outline-dark">
                      <b-icon-box-arrow-down class="mr-1"></b-icon-box-arrow-down>파일 다운로드
                    </b-button>
                  </b-col>
                </b-row>
              </div>
              <div v-else>
                <small>파일을 추가해보세요.</small>
              </div>
              <b-row>
                <b-icon-plus-square class="mr-1"></b-icon-plus-square>
              </b-row>
            </b-container>
          </div>
        </b-tab>
        <!-- <b-tab title="Disabled" disabled>
          <p class="tabContents">I'm a disabled tab!</p>
        </b-tab>-->
      </b-tabs>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  components: {},
  // props: {
  //   pid: Number,
  // },
  data: () => {
    return {
      uid: localStorage["uid"],
      pjtDetail: [],
      updateP: false,
      updateDateState: false,
      updateContentsState: false,
      updateTitleState: false,
      newTag: "",
    };
  },
  created() {
    console.log(this.$route.params.pid);
    axios
      .get(this.$SERVER_URL + `/portfolio/${this.$route.params.pid}`, {
        params: { pid: this.$route.params.pid },
      })
      .then((response) => {
        console.log(response);
        if (response.data.status) {
          this.pjtDetail = response.data.object;
        }
      });
  },
  methods: {
    updateDate() {
      this.updateDateState = !this.updateDateState
    },
    updateContents() {
      this.updateContentsState = !this.updateContentsState
    },
    updateTitle() {
      this.updateTitleState = !this.updateTitleState
    },
    sendUpdateInfo() {
      axios
      .put(this.$SERVER_URL + `/portfolio`, {
        uid: this.uid,
        pid: this.pjtDetail.pid,
        title: this.pjtDetail.title,
        contents: this.pjtDetail.contents,
        startDate: this.pjtDetail.start_date,
        endDate: this.pjtDetail.end_date,
      })
      .then((response) => {
        // alert('수정완료')
      })
      .catch((error) => {
        console.log(error)
      })
    },
    deleteTag(tid) {
      axios
      .delete((this.$SERVER_URL + `/tag`), {
        data: {
        pid: this.pjtDetail.pid,
        tid: tid,
      }})
      .then((response) => {
        // alert('태그삭제')
        // 태그 다시 받아야 새로고침효과
        axios
        .get(this.$SERVER_URL + `/portfolio/${this.pjtDetail.pid}`, {
          params: { pid: this.pjtDetail.pid },
        })
        .then((response) => {
          if (response.data.status) {
            this.pjtDetail = response.data.object;
          }
        });
      })
      .catch((error) => {
        console.log(error)
      })
    },
    addNewTag(newTag) {
      console.log(this.pjtDetail.pid)
      console.log(newTag)
      let form = new FormData();
      form.append("pid", this.pjtDetail.pid)
      form.append("tag", newTag)
      axios
      .post(this.$SERVER_URL + `/portfolio/tag`, form)
      .then((response) => {
        // alert("태그!")
        // 태그 다시 받아야 새로고침효과..
        axios
        .get(this.$SERVER_URL + `/portfolio/${this.pjtDetail.pid}`, {
          params: { pid: this.pjtDetail.pid },
        })
        .then((response) => {
          if (response.data.status) {
            this.pjtDetail = response.data.object;
          }
        });
        this.newTag = ""
      })
      .catch((error) => {
        console.log(error)
      })
    },
  },
  
};
</script>

<style>
.active {
  background-color: rgb(212, 211, 211) !important;
  border-bottom-color: rgb(212, 211, 211) !important;
}
.tabContents {
  background-color: rgb(212, 211, 211);
  padding: 10px;
  text-align: left;
}
.tabs {
  margin-top: 40px;
  margin-bottom: 40px;
  margin-left: 50px;
  margin-right: 50px;
}
.title {
  text-align: left;
  margin-left: 120px;
}
.input-contents {
  width: 90%;
  height: 20rem;
}
</style>