<script>
    import { push } from 'svelte-spa-router'
    import spring from "../lib/api"
    import Error from "../components/Error.svelte"
  import { access_token } from '../lib/store';

    let error = {detail:[]}
    let portFolioPageUrl = ''
    let content = ''

    function post_question(event) {
        event.preventDefault()
        let url = "/api/portfolio"
        let params = {
            portFolioPageUrl: portFolioPageUrl,
            content: content,
        }
        spring('post', url, params, 
            (json) => {
                push("/")
            },
            (json_error) => {
                error = json_error
            },
            $access_token
        )
    }
</script>

<div class="container">
    <h5 class="my-3 border-bottom pb-2">질문 등록</h5>
    <Error error={error} />
    <form method="post" class="my-3">
        <div class="mb-3">
            <label for="subject">외부 Url</label>
            <input type="text" class="form-control" bind:value="{portFolioPageUrl}">
        </div>
        <div class="mb-3">
            <label for="content">내용</label>
            <textarea class="form-control" rows="10" bind:value="{content}"></textarea>
        </div>
        <button class="btn btn-primary" on:click="{post_question}">저장하기</button>
    </form>
</div>