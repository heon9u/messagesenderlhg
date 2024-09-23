<template>

    <v-data-table
        :headers="headers"
        :items="myMessage"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'MyMessageView',
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
            myMessage : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/myMessages'))

            temp.data._embedded.myMessages.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.myMessage = temp.data._embedded.myMessages;
        },
        methods: {
        }
    }
</script>

