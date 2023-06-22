<script>
    import { push } from 'svelte-spa-router'
    import spring from "../lib/api"
    import Error from "../components/Error.svelte"
    import { access_token, username, is_login } from "../lib/store"

    let error = {detail:[]}
    let login_id = ""
    let login_password = ""

    function login(event) {
        event.preventDefault()
        let url = "/api/user/token"
        let params = {
            userId: login_id,
            userPassword: login_password,
        }
        spring('post', url, params, 
            (response) => {
                $access_token = response.access_token
                $username = response.json.username
                $is_login = true
                push("/")
                console.log(response.access_token)
            },
            (json_error) => {
                error = json_error
            }
        )
    }
</script>

<div class="container">
    <h5 class="my-3 border-bottom pb-2">로그인</h5>
    <Error error={error} />
    <form method="post">
        <div class="mb-3">
            <label for="username">아이디</label>
            <input type="text" class="form-control" id="username" bind:value="{login_id}">
        </div>
        <div class="mb-3">
            <label for="password">비밀번호</label>
            <input type="password" class="form-control" id="password" bind:value="{login_password}">
        </div>
        <button type="submit" class="btn btn-primary" on:click="{login}">로그인</button>
    </form>
</div>