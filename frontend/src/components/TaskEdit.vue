<template>
    <div v-if="task">
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
    watch: {
        task(newTask) {
            this.updatedTask = { ...newTask, dueDate: new Date(newTask.dueDate).toISOString().split('T')[0] };
        }
    },
    data() {
        return {
            updatedTask: { ...this.task, dueDate: new Date(this.task.dueDate).toISOString().split('T')[0] }
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
