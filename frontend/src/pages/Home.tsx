import React from 'react';

export default function Home() {
    return (
        <>
            {todos.map((todo) => (
                <li key={todo.id}>{todo.title}</li>
            ))}
        </>
    );
}
