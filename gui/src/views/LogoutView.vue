<template>
    <div>
        <h1>Logging out...</h1>
        <button @click="logout">Logout</button>
    </div>
</template>

<script>
export default {
    methods: {
        async logout() {
            try {
                const response = await fetch('http://localhost:8080/user/logout', {
                    method: 'POST',
                    credentials: 'include',
                });

                if (response.ok) {
                    alert('Logged out successfully!');
                } else {
                    console.error('Logout failed');
                }
            } catch (error) {
                console.error('Error logging out:', error);
            }
        },
    },
};
</script>