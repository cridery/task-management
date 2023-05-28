<template>
    <div v-if="task">
        <h2>
            <input v-model="editedTask.title" type="text" />
        </h2>
        <p>
            <textarea v-model="editedTask.description"></textarea>
        </p>
        <p>
            Status:
            <select v-model="editedTask.status">
                <option value="TODO">To Do</option>
                <option value="IN_PROGRESS">In Progress</option>
                <option value="DONE">Done</option>
            </select>
        </p>
        <p>
            Due:
            <input v-model="editedTask.dueDate" type="date" />
        </p>
        <button @click="saveChanges">Save</button>
    </div>
</template>

<script>
export default {
    name: 'TaskDetail',
    props: {
        task: {
            type: Object,
            required: true,
            default: null
        }
    },
    data() {
        return {
            editedTask: null
        };
    },
    watch: {
        task: {
            immediate: true,
            handler(newTask) {
                this.editedTask = { ...newTask };
            }
        }
    },
    methods: {
        saveChanges() {
            // Emit an event with the edited task to notify the parent component
            this.$emit('task-edited', this.editedTask);
        }
    }
};
</script>
