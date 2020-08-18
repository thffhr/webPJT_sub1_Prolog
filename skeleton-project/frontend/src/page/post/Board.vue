<template>
  <div
      :id="id"
      class="board"
      @dragover.prevent
      @drop.prevent="drop"
      >
    <slot />
  </div>
</template>

<script>
import EventBus from './EventBus';

export default {
  name: 'Board',
  props: ['id'],

  created(){
    this.msg
    EventBus.$on("cardToBoard_cardId", (payload) => {
     /*  this.msg = payload;
      this.delete(this.msg) } */}
      )
  }
  ,
  methods: {
    drop: e=>{
      const card_id = e.dataTransfer.getData('card_id');
      const parent_id = e.dataTransfer.getData('parent_id');

      const card = document.getElementById(card_id);

      card.style.display = "block";
      
      alert("이놈아이디" + e.target.id);
      
      //같은 부모에 드래그앤 드랍하면 아무일도 없음..
      if(e.target.id == parent_id){
      
      }
      //만약에 같은 요소를 선택했으면
      else if(e.target.id == ""){
        
      }
      //나머지는 정상..?
      else{
        
         EventBus.$emit('test', "이벤트 실행 ㄱㄱ");
      }

      //e.target.createElement("div");
      e.target.appendChild(card);
      

    },
  
  /* delete:function(cardId){
    
    alert("끝")
    const card = document.getElementById(cardId);
    card.parentNode.removeChild(cardId);

  } */
  }
}
</script>
<style>

</style>