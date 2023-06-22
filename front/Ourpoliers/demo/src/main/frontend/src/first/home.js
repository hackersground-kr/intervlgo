import React, { Component } from "react";
import axios from "axios";
import "./home.css";

class home extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    const [hello, setHello] = useState("");

    useEffect(() => {
      axios
        .post("/api/user/join")
        .then((response) => setHello(response.data))
        .catch((error) => console.log(error));
    }, []);

    return (
      <div className="App">
        <section class="hero-heading-center wf-section">
          <div class="container-2">
            <h1 class="centered-heading margin-bottom-32px">Ourfolio</h1>
          </div>
        </section>
        <div class="navbar-logo-center wf-section">
          <div
            data-animation="default"
            data-collapse="medium"
            data-duration="400"
            data-easing="ease"
            data-easing2="ease"
            role="banner"
            class="navbar-logo-center-container shadow-three w-nav"
          >
            <div class="container-2">
              <div class="navbar-wrapper-three">
                <nav
                  role="navigation"
                  class="nav-menu-wrapper-three w-nav-menu"
                >
                  <div class="nav-menu-three">
                    <ul role="list" class="nav-menu-block w-list-unstyled">
                      <li>
                        <a href="#" class="nav-link">
                          My portfolio
                        </a>
                      </li>
                      <li>
                        <a href="#" class="nav-link">
                          see other portfolio
                        </a>
                      </li>
                      <li>
                        <div
                          data-hover="false"
                          data-delay="0"
                          class="nav-dropdown w-dropdown"
                        ></div>
                      </li>
                    </ul>
                    <ul role="list" class="nav-menu-block w-list-unstyled">
                      <li class="mobile-margin-top-10">
                        <a href="#" class="button-primary w-button">
                          sign in
                        </a>
                      </li>
                    </ul>
                  </div>
                </nav>
                <div class="menu-button-2 w-nav-button">
                  <div class="w-icon-nav-menu"></div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div
          id="w-node-_54ee92b0-7306-8986-4fe8-e13fff369625-980428fd"
          class="w-layout-layoutwf-layout-layout"
        >
          <div
            id="w-node-_5a4f5cc6-0e9e-a982-8fa3-c20e7b393e21-980428fd"
            class="w-layout-cell"
          >
            <div class="text-block">Ranking for Today on ourFolio</div>
            <img
              src="https://d3e54v103j8qbb.cloudfront.net/plugins/Basic/assets/placeholder.60f9b1840c.svg"
              loading="lazy"
              width="500"
              height="500"
              alt=""
            />
            <div>Best Portfolio</div>
          </div>
          <div
            id="w-node-_3a4428ed-926c-0e43-3379-1c594df1f233-980428fd"
            class="w-layout-cell"
          >
            <section class="section-2 wf-section">
              <div class="section cc-contact">
                <div class="container">
                  <div class="contact">
                    <div class="contact-headline"></div>
                    <div class="contact-form-wrap">
                      <div class="w-form">
                        <form
                          id="wf-form-password-form"
                          name="wf-form-password-form"
                          data-name="password form"
                          method="get"
                          class="contact-form"
                          data-wf-page-id="6493019cb00e318e980428fd"
                          data-wf-element-id="d783a17e-0b35-a13a-0448-a852d0df4a30"
                        >
                          <div class="w-layout-grid contact-form-grid">
                            <div id="w-node-d783a17e-0b35-a13a-0448-a852d0df4a32-d0df4a24">
                              <label for="ID">ID</label>
                              <input
                                type="text"
                                class="text-field w-input"
                                maxlength="256"
                                name="ID"
                                data-name="ID"
                                placeholder="Enter your ID"
                                id="ID"
                              />
                            </div>
                            <div id="w-node-d783a17e-0b35-a13a-0448-a852d0df4a36-d0df4a24">
                              <label for="password-2">Password</label>
                              <input
                                type="password"
                                class="text-field w-input"
                                maxlength="256"
                                name="password"
                                data-name="password"
                                placeholder="Enter your password"
                                id="password-2"
                                required=""
                              ></input>
                            </div>
                            <input
                              type="submit"
                              value="log in"
                              data-wait="Please wait..."
                              id="w-node-d783a17e-0b35-a13a-0448-a852d0df4a3e-d0df4a24"
                              class="button w-button"
                            />
                          </div>
                        </form>
                        <div class="status-message cc-success-message w-form-done">
                          <div>
                            Thank you! Your submission has been received!
                          </div>
                        </div>
                        <div class="status-message cc-error-message w-form-fail">
                          <div>
                            Oops! Something went wrong while submitting the
                            form.
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </section>
          </div>
        </div>
        <div class="section">
          <div class="container"></div>
        </div>
        <div class="section">
          <div id="works-grid" class="w-layout-grid works-grid">
            <div id="w-node-dff26f68-5731-3efa-d3dd-8f65c72d8fa5-980428fd">
              <div class="work-description">
                <div
                  id="w-node-dda9ae0d-47ba-8fe7-57a8-d9d8ff221d82-980428fd"
                  class="w-layout-layout wf-layout-layout"
                >
                  <div
                    id="w-node-dda9ae0d-47ba-8fe7-57a8-d9d8ff221d83-980428fd"
                    class="w-layout-cell"
                  ></div>
                </div>
                <a href="/work/project-1" class="project-name-link">
                  Best 조회수
                </a>
              </div>
            </div>
            <div id="w-node-dff26f68-5731-3efa-d3dd-8f65c72d8fad-980428fd">
              <div class="work-description">
                <div
                  id="w-node-_4844e245-30d8-58d3-be81-b7105cf4d762-980428fd"
                  class="w-layout-layout wf-layout-layout"
                >
                  <div
                    id="w-node-_4844e245-30d8-58d3-be81-b7105cf4d764-980428fd"
                    class="w-layout-cell"
                  ></div>
                </div>
                <a href="/work/project-2" class="project-name-link">
                  실시간 댓글
                </a>
              </div>
            </div>
          </div>
        </div>
        <script
          src="https://d3e54v103j8qbb.cloudfront.net/js/jquery-3.5.1.min.dc5e7f18c8.js?site=6493019cb00e318e98042857"
          type="text/javascript"
          integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
          crossorigin="anonymous"
        ></script>
        <script src="js/webflow.js" type="text/javascript"></script>
      </div>
    );
  }
}

export default home;
