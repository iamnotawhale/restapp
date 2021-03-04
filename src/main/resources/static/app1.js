
const currentUserFetchService = {
    head: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Referer': null
    },

    getCurrentUser: () => fetch("/rest_admin/user")
}

async function getCurrentUsers() {
    let table = $('#mainTableWithUsers');
    let currentUser = await currentUserFetchService.getCurrentUser().then(rest => rest.json());

    $("#currentUserEmail").html(`${currentUser.email} with roles ${currentUser.roles.map(role=>role.role).join()}`)


    await currentUserFetchService.getCurrentUser()
        .then(res => res.json())
        .then(user => {
                let tableFilling = `$(
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.login}</td>
                            <td>${user.email}</td>
                            <td>${user.roles.map(role=>role.role).join(',')}</td>

                        </tr>
                )`;
                table.append(tableFilling);
            })
        }
getCurrentUsers();

