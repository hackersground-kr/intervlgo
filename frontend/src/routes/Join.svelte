<script>
    import { push } from 'svelte-spa-router'
    import spring from "../lib/api"
    import Error from "../components/Error.svelte"

    let error = {detail:[]}
    let username =''
    let userId = ''
    let userPassword = ''
    let region = ''
    let occupation = ''

    function post_user(event) {
        event.preventDefault()
        let url = "/api/user/join"
        let params = {
            username: username,
            userId: userId,
            userPassword: userPassword,
            region: region,
            occupation: occupation
        }
        spring('post', url, params, 
            (json) => {
                push('/user-login')
            },
            (json_error) => {
                error = json_error
            }
        )
    }
</script>

<div class="container">
    <h5 class="my-3 border-bottom pb-2">회원 가입</h5>
    <Error error={error} />
    <form method="post">
        <div class="mb-3">
            <label for="username">사용자 이름</label>
            <input type="text" class="form-control" id="username" bind:value="{username}">
        </div>
        <div class="mb-3">
            <label for="userId">아이디</label>
            <input type="text" class="form-control" id="userId" bind:value="{userId}">
        </div>
        <div class="mb-3">
            <label for="password">비밀번호</label>
            <input type="text" class="form-control" id="password" bind:value="{userPassword}">
        </div>
        <div class="mb-3">
            <label for="occupation">직종</label>
            <input type="text" class="form-control" id="occupation" list="occupationList" bind:value="{occupation}">
            <datalist id="occupationList">
                <option value="무직"></option>
                <option value="마케팅"></option>
                <option value="IT 및 웹 개발"></option>
                <option value="디자이너"></option>
                <option value="서비스"></option>
                <option value="의료"></option>
                <option value="교육"></option>
                <option value="연구 개발"></option>
                <option value="기타"></option>
            </datalist>
        </div>
        <div class="mb-3">
            <label for="region">지역</label>
            <input type="text" class="form-control" id="region" list="regionList" bind:value="{region}">
            <datalist id="regionList">
                <option value="대구시"></option>
                <option value="경산시"></option>
                <option value="경주시"></option>
                <option value="구미시"></option>
                <option value="김천시"></option>
                <option value="문경시"></option>
                <option value="상주시"></option>
                <option value="안동시"></option>
                <option value="영주시"></option>
                <option value="영천시"></option>
                <option value="포항시"></option>
                <option value="기타"></option>
            </datalist>
        </div>

        <button type="submit" class="btn btn-primary" on:click="{post_user}">생성하기</button>
    </form>
</div>