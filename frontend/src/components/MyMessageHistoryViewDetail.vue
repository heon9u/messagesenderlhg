<template>

    <v-card outlined>
        <v-card-title>
            MyMessageHistory # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <String label="MessageId" v-model="item.messageId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="UserContact" v-model="item.userContact" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Mno" v-model="item.mno" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="SendTime" v-model="item.sendTime" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="ChatbotId" v-model="item.chatbotId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Result" v-model="item.result" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Description" v-model="item.description" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'MyMessageHistoryViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/myMessageHistories/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

