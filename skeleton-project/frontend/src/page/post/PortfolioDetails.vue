<template>
  <div>
    <h2 class="title">
      PJT 이름
      <b-button size="sm" variant="outline-dark">전체 파일 다운로드</b-button>
    </h2>
    <div class="tabs">
      <!-- <b-tabs content-class align="center"> -->
      <b-tabs content-class fill>
        <b-tab title="프로젝트 정보" active>
          <b-container class="tabContents">
            <b-row>
              <b-col class="mt-4 mb-2">
                <p>프로젝트 진행 기간 : 2020-07-30 ~ 2020-07-30</p>
              </b-col>
            </b-row>
            <b-row>
              <b-col class="mt-2 mb-2">
                <h5>
                  <!-- 태그 출력 -->
                  <b-badge pill class="mr-3" id="tag" text-variant="black">태그 1</b-badge>
                  <b-badge pill class="mr-3" id="tag" text-variant="black">태그 2</b-badge>
                  <b-badge pill class="mr-3" id="tag" text-variant="black">태그 3</b-badge>
                  <b-badge pill class="mr-3" id="tag" text-variant="black">태그 4</b-badge>...
                </h5>
              </b-col>
            </b-row>
            <b-row>
              <b-col class="mt-2 mb-2">
                <h5>프로젝트 정의</h5>
                <p>이 프로젝트는 영국에서 최초로 시작되어 일년에 한 바퀴 돌면서 받는 사람에게 행운을 주었고 지금은 당신에게로 옮겨진 이 편지는 4일 안에 당신 곁을 떠나야 합니다. 이 편지를 포함해서 7통을 행운이 필요한 사람에게 보내 주셔야 합니다. 복사를 해도 좋습니다. 혹 미신이라 하실지 모르지만 사실입니다. 영국에서 HGXWCH이라는 사람은 1930년에 이 편지를 받았습니다. 그는 비서에게 복사해서 보내라고 했습니다. 며칠 뒤에 복권이 당첨되어 20억을 받았습니다. 어떤 이는 이 편지를 받았으나 96시간 이내 자신의 손에서 떠나야 한다는 사실을 잊었습니다. 그는 곧 사직되었습니다. 나중에야 이 사실을 알고 7통의 편지를 보냈는데 다시 좋은 직장을 얻었습니다. 미국의 케네디 대통령은 이 편지를 받았지만 그냥 버렸습니다. 결국 9일 후 그는 암살 당했습니다. 기억해 주세요. 이 편지를 보내면 7년의 행운이 있을 것이고 그렇지 않으면 3년의 불행이 있을 것입니다. 그리고 이 편지를 버리거나 낙서를 해서는 절대로 안됩니다. 7통입니다. 이 편지를 받은 사람은 행운이 깃들 것입니다. 힘들겠지만 좋은게 좋다고 생각하세요. 7년의 행운을 빌면서...</p>
              </b-col>
            </b-row>
          </b-container>
          <div></div>
        </b-tab>
        <b-tab title=" 파일 목록 ">
          <div class="tabContents">
            <b-container class="tabContents">
              <b-row>
                <b-col class="mt-4 mb-2">
                  파일 1
                  <b-button size="sm" variant="outline-dark">파일 다운로드</b-button>
                </b-col>
              </b-row>
              <b-row>
                <b-col class="mt-2 mb-2">
                  파일 2
                  <b-button size="sm" variant="outline-dark">파일 다운로드</b-button>
                </b-col>
              </b-row>
              <b-row>
                <b-col class="mt-2 mb-2">
                  파일 3
                  <b-button size="sm" variant="outline-dark">파일 다운로드</b-button>
                </b-col>
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
export default {
  components: {},
  props: {
    pid: {
      type: Number,
      default: 0,
    },
  },
  data: () => {
    return {};
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
</style>