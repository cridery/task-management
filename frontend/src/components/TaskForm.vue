<template>
    <form @submit.prevent="addTask" class="space-y-4">
        <h2>Add a New Task</h2>
        <div class="">
        <!-- <label for="title" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white" >Large input</label> -->
        <!-- Write placeholder -->
            <input type="text" 
                id="title" 
                v-model="title" 
                required class="block w-full p-4 text-gray-900 border border-gray-300 rounded-lg bg-gray-50 sm:text-md focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="Untitled"
            >
        </div>
        <div>
            <label for="description"></label>
            <!-- <textarea id="description" v-model="description" required></textarea> -->
            <textarea id="description" 
            v-model="description"
            required
            rows="4" 
            class="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" 
            placeholder="Description"></textarea>
        </div>
        <div>
            <label for="dueDate" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                Due Date:
            </label>
            <input class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pl-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" type="date" id="dueDate" v-model="dueDate" required />
        </div>
        <div>
            <label for="status" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Status:</label>
            <select 
                id="status"
                v-model="status" 
                required
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                    <option value="TODO">To Do</option>
                    <option value="IN_PROGRESS">In Progress</option>
                    <option value="DONE">Done</option>
            </select>
        </div>
        <div class="flex justify-center w-full items-center">
            <button type="submit" class="py-2.5 px-5 mr-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700">Add Task</button>
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