<script>
  import Router from 'svelte-spa-router'
  import Navigation from "./components/Navigation.svelte"
  import Join from "./routes/Join.svelte"
  import Login from './routes/Login.svelte';
  import Home from './routes/Home.svelte';
  import PortfolioCreate from './routes/PortfolioCreate.svelte';

  const routes = {
      "/": Home,
      "/user-join": Join,
      "/user-login": Login,
      "/portfolio-create": PortfolioCreate,
  }
</script>

<Navigation />
<Router {routes}/>