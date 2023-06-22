import React, { Component, useState, useEffect } from "react";
import { Routes, Route, Link, BrowserRouter } from "react-router-dom";
import axios from "axios";
import "./webflow.css";
import "./normalize.css";
import "./singup.css";

function App() {
  return (
    <div className="MainDiv">
      <div class="section cc-contact">
        <div class="container">
          <div class="contact">
            <div class="contact-headline">
              <h3 class="heading">회원가입</h3>
            </div>
            <div
              id="w-node-_4d06cdea-b624-16cc-ccca-c7c2689a8741-d0df4a24"
              class="w-layout-layout wf-layout-layout"
            >
              <div
                id="w-node-e177d939-266e-9ee7-0611-c8f35c1db983-d0df4a24"
                class="w-layout-cell"
              >
                <div>아이디</div>
              </div>
              <div
                id="w-node-_5dfd2094-abf8-3f56-38ea-3b99a4acaf6d-d0df4a24"
                class="w-layout-cell"
              >
                <div class="w-form">
                  <form
                    id="email-form-2"
                    name="email-form-2"
                    data-name="Email Form 2"
                    method="get"
                    data-wf-page-id="64932eae0ac6766ef47928ac"
                    data-wf-element-id="b34d1c23-1d68-d04a-ed53-6d7ef8af8966"
                  >
                    <input
                      type="text"
                      class="w-input"
                      maxlength="256"
                      name="id"
                      data-name="id"
                      placeholder="Enter Your id"
                      id="id"
                      required=""
                    />
                  </form>
                  <div class="w-form-done">
                    <div>Thank you! Your submission has been received!</div>
                  </div>
                  <div class="w-form-fail">
                    <div>
                      Oops! Something went wrong while submitting the form.
                    </div>
                  </div>
                </div>
              </div>
              <div
                id="w-node-_9de49bf2-b021-59cc-4b08-c9b44b2b7790-d0df4a24"
                class="w-layout-cell"
              >
                <div>비밀번호</div>
              </div>
              <div
                id="w-node-d9053fe3-f16f-fe17-6aee-7c54c0d65956-d0df4a24"
                class="w-layout-cell"
              >
                <div class="w-form">
                  <form
                    id="email-form-3"
                    name="email-form-3"
                    data-name="Email Form 3"
                    method="get"
                    data-wf-page-id="64932eae0ac6766ef47928ac"
                    data-wf-element-id="773191f8-453a-cf1c-808c-c14263fff65e"
                  >
                    <input
                      type="password"
                      class="w-input"
                      maxlength="256"
                      name="pass1"
                      data-name="pass1"
                      placeholder="Enter your password"
                      id="pass"
                      required=""
                    />
                  </form>
                  <div class="w-form-done">
                    <div>Thank you! Your submission has been received!</div>
                  </div>
                  <div class="w-form-fail">
                    <div>
                      Oops! Something went wrong while submitting the form.
                    </div>
                  </div>
                </div>
              </div>
              <div
                id="w-node-_2529d618-edc8-7ca2-ad72-f772f3c26e77-d0df4a24"
                class="w-layout-cell"
              >
                <div>비밀번호 재확인</div>
              </div>
              <div
                id="w-node-f93efbd7-805c-6188-d278-27520606b8cf-d0df4a24"
                class="w-layout-cell"
              >
                <div class="w-form">
                  <form
                    id="email-form-4"
                    name="email-form-4"
                    data-name="Email Form 4"
                    method="get"
                    data-wf-page-id="64932eae0ac6766ef47928ac"
                    data-wf-element-id="c81ed954-87d6-6bcf-c2ff-512cd882c9ba"
                  >
                    <input
                      type="password"
                      class="w-input"
                      maxlength="256"
                      name="pass2"
                      data-name="pass2"
                      placeholder="Enter your password"
                      id="pass-3"
                      required=""
                    />
                  </form>
                  <div class="w-form-done">
                    <div>Thank you! Your submission has been received!</div>
                  </div>
                  <div class="w-form-fail">
                    <div>
                      Oops! Something went wrong while submitting the form.
                    </div>
                  </div>
                </div>
              </div>
              <div
                id="w-node-_2576b4c5-db0c-7c3d-3613-5c39039aef04-d0df4a24"
                class="w-layout-cell"
              >
                <div>직군</div>
              </div>
              <div
                id="w-node-ee7012a6-6c17-0b18-8d76-262f68a294d5-d0df4a24"
                class="w-layout-cell"
              >
                <div class="w-form">
                  <form
                    id="email-form-5"
                    name="email-form-5"
                    data-name="Email Form 5"
                    method="get"
                    data-wf-page-id="64932eae0ac6766ef47928ac"
                    data-wf-element-id="0bb7708c-ebd5-a1dc-eaa5-91efdc9d1e44"
                  >
                    <select
                      id="jobSelection"
                      name="jobSelection"
                      data-name="jobSelection"
                      required=""
                      class="select-field-2 w-select"
                    >
                      <option value="0">무직</option>
                      <option value="1">마케팅</option>
                      <option value="2">IT 및 웹 개발</option>
                      <option value="3">디자이너</option>
                      <option value="4">서비스</option>
                      <option value="5">의료</option>
                      <option value="6">교육</option>
                      <option value="7">연구 개발</option>
                      <option value="8">기타</option>
                    </select>
                  </form>
                  <div class="w-form-done">
                    <div>Thank you! Your submission has been received!</div>
                  </div>
                  <div class="w-form-fail">
                    <div>
                      Oops! Something went wrong while submitting the form.
                    </div>
                  </div>
                </div>
              </div>
              <div
                id="w-node-e5616d93-5e9d-3bcc-72e8-25ac77d110e0-d0df4a24"
                class="w-layout-cell"
              >
                <div>지역</div>
              </div>
              <div
                id="w-node-_8b8961f2-a420-666c-4e0e-3299fd2640b2-d0df4a24"
                class="w-layout-cell"
              >
                <div class="w-form">
                  <form
                    id="email-form-6"
                    name="email-form-6"
                    data-name="Email Form 6"
                    method="get"
                    data-wf-page-id="64932eae0ac6766ef47928ac"
                    data-wf-element-id="e791777b-9e65-81fc-a1e3-13a17cf9c702"
                  >
                    <select
                      id="region"
                      name="region"
                      data-name="region"
                      required=""
                      class="select-field-3 w-select"
                    >
                      <option value="001">대구시</option>
                      <option value="002">경산시</option>
                      <option value="003">경주시</option>
                      <option value="004">구미시</option>
                      <option value="005">김천시</option>
                      <option value="006">문경시</option>
                      <option value="007">상주시</option>
                      <option value="008">안동시</option>
                      <option value="009">영주시</option>
                      <option value="010">영천시</option>
                      <option value="011">포항시</option>
                      <option value="012">기타</option>
                    </select>
                  </form>
                  <div class="w-form-done">
                    <div>Thank you! Your submission has been received!</div>
                  </div>
                  <div class="w-form-fail">
                    <div>
                      Oops! Something went wrong while submitting the form.
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="contact-form-wrap">
              <div class="w-form">
                <form
                  id="wf-form-Email-Form"
                  name="wf-form-Email-Form"
                  data-name="Email Form"
                  method="get"
                  class="contact-form"
                  data-wf-page-id="64932eae0ac6766ef47928ac"
                  data-wf-element-id="d783a17e-0b35-a13a-0448-a852d0df4a30"
                >
                  <div class="w-layout-grid contact-form-grid">
                    <div id="w-node-d783a17e-0b35-a13a-0448-a852d0df4a3a-d0df4a24">
                      <label for="Message" class="field-label-2">
                        Introduction
                      </label>
                      <textarea
                        id="Message"
                        name="Message"
                        placeholder="Enter your message"
                        maxlength="5000"
                        data-name="Message"
                        class="text-field cc-textarea w-input"
                      ></textarea>
                    </div>
                  </div>
                  <div className="buttonSubmit">
                    <input
                      type="submit"
                      value="Submit"
                      data-wait="Please wait..."
                      class="button w-button"
                    />
                  </div>
                </form>
                <div class="status-message cc-success-message w-form-done">
                  <div>Thank you! Your submission has been received!</div>
                </div>
                <div class="status-message cc-error-message w-form-fail">
                  <div>
                    Oops! Something went wrong while submitting the form.
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <script
        src="https://d3e54v103j8qbb.cloudfront.net/js/jquery-3.5.1.min.dc5e7f18c8.js?site=64932eae0ac6766ef4792888"
        type="text/javascript"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
        crossorigin="anonymous"
      ></script>
      <script src="webflow.js" type="text/javascript"></script>
    </div>
  );
}

export default App;
