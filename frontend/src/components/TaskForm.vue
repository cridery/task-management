<template>
    <form @submit.prevent="addTask">
        <h2>Add a New Task</h2>
        <div>
            <label for="title">Title</label>
            <input type="text" id="title" v-model="title" required />
        </div>
        <div>
            <label for="description">Description</label>
            <textarea id="description" v-model="description" required></textarea>
        </div>
        <div>
            <label for="dueDate">
                Due Date:
            </label>
            <input type="date" id="dueDate" v-model="dueDate" required />
        </div>
        <div>
            <label for="status">Status</label>
            <select id="status" v-model="status" required>
                <option value="TODO">To Do</option>
                <option value="IN_PROGRESS">In Progress</option>
                <option value="DONE">Done</option>
            </select>
        </div>
        <div>
            <button type="submit">Add Task</button>
        </div>
    </form>
</template>


<script>
import { ref } from 'vue';
import { store } from '../store';

export default {
    name: 'TaskForm',
    setup() {
        const title = ref('');
        const description = ref('');
        const dueDate = ref('');
        const status = ref('TODO');

        const addTask = async () => {
            try {
                const taskDate = new Date(dueDate.value);
                const task = {
                    title: title.value,
                    description: description.value,
                    dueDate: taskDate.toISOString(),
                    status: status.value
                };
                await store.dispatch('addTask', task);
                title.value = '';
                description.value = '';
                dueDate.value = '';
                status.value = 'TODO';
            } catch (error) {
                console.error(error);
            }
        };

        return { addTask, title, description, dueDate, status }
    }
}
</script>