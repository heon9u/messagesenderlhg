<template>

    <v-data-table
        :headers="headers"
        :items="myMessageHistory"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'MyMessageHistoryView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "messageId", value: "messageId" },
                { text: "userContact", value: "userContact" },
                { text: "mno", value: "mno" },
                { text: "sendTime", value: "sendTime" },
                { text: "chatbotId", value: "chatbotId" },
                { text: "result", value: "result" },
                { text: "description", value: "description" },
            ],
            myMessageHistory : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/myMessageHistories'))

            temp.data._embedded.myMessageHistories.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.myMessageHistory = temp.data._embedded.myMessageHistories;
        },
        methods: {
        }
    }
</script>

