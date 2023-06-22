<script>

    import spring from "../lib/api"
    import { link } from 'svelte-spa-router'
    import { page } from "../lib/store"
    // @ts-ignore
    import moment from 'moment/min/moment-with-locales'
    moment.locale('ko')


    let portfolio_list = []
    let size = 10
    let total = 0
    //let total_page_list = Array(Math.ceil(total/10))
    $: total_page = Math.ceil(total/10)

    function get_portfolio_list(_page) {
        let params = {
            page: _page
        }
        spring('get', '/api/portfolio/list',params, (json) => {
            portfolio_list = json.portfolio_list
            $page = _page
            total = json.total
        })
        
    }
    

    $: get_portfolio_list($page)
</script>

<div class="container my-3">
    <table class="table">
        <thead>
        <tr class="table-dark">
            <th>번호</th>
            <th>제목</th>
            <th>작성일시</th>
        </tr>
        </thead>
        <tbody>
        {#each portfolio_list as portfolio, i}
        <tr>
            <td>{($page * size) + i+1}</td>
            <td>
                <a use:link href="/detail/{portfolio.id}">{portfolio.subject}</a>
                {#if portfolio.answers.length > 0 }
                <span class="text-danger small mx-2">{portfolio.answers.length}</span>
                {/if}
            </td>
            <td>{moment(portfolio.create_date).format("YYYY년 MM월 DD일 a hh:mm")}</td>
        </tr>
        {/each}
        </tbody>
    </table>
    <!-- 페이징처리 시작 -->
    <ul class="pagination justify-content-center">
        <!-- 이전페이지 -->
        <li class="page-item {$page <= 0 && 'disabled'}">
            <button class="page-link" on:click="{() => get_portfolio_list($page-1)}">이전</button>
        </li>
        <!-- 페이지번호 -->
        {#each Array(total_page) as _, loop_page}
        {#if loop_page >= $page-5 && loop_page <= $page+5} 
        <li class="page-item {loop_page === $page && 'active'}">
            <button on:click="{() => get_portfolio_list(loop_page)}" class="page-link">{loop_page+1}</button>
        </li>
        {/if}
        {/each}
        <!-- 다음페이지 -->
        <li class="page-item {$page >= total_page-1 && 'disabled'}">
            <button class="page-link" on:click="{() => get_portfolio_list($page+1)}">다음</button>
        </li>
    </ul>
    <!-- 페이징처리 끝 -->
    <a use:link href="/portfolio-create" class="btn btn-primary">포트폴리오 등록하기</a>  <a use:link href="/portfolio-update" class="btn btn-primary">포트폴리오 수정하기</a>
</div>