<template>
    <div v-if="task">
        <h2>Edit Task</h2>
        <form @submit.prevent="saveTask">
            <div>
                <label for="title">Title</label>
                <input type="text" id="title" v-model="updatedTask.title" required />
            </div>
            <div>
                <label for="description">Description</label>
                <textarea id="description" v-model="updatedTask.description" required></textarea>
            </div>
            <div>
                <label for="status">Status</label>
                <select id="status" v-model="updatedTask.status" required>
                    <option value="TODO">To Do</option>
                    <option value="IN_PROGRESS">In Progress</option>
                    <option value="DONE">Done</option>
                </select>
            </div>
            <div>
                <label for="dueDate">Due Date</label>
                <input type="date" id="dueDate" v-model="updatedTask.dueDate" required />
            </div>
            <button type="submit">Save</button>
        </form>
    </div>
</template>

<script>
export default {
    name: 'TaskEdit',
    props: {
        task: {
            type: Object,
            required: true,
            default: null
        }
    },
    data() {
        return {
            updatedTask: { ...this.task } // Copy the task object to a separate data property for editing
        };
    },
    methods: {
        saveTask() {
            // Emit the 'task-updated' event with the updated task data
            this.$emit('task-updated', this.updatedTask);
        }
    }
};
</script>

<style scoped>
/* Add your component-specific styles here */
</style>
