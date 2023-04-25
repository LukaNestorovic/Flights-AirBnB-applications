import React, { useEffect } from "react";
import { withRouter } from "./with-router";

const parseJwt = (token:any) => {
    try {
        return JSON.parse(atob(token.split('.')[1]));
    } catch (e) {
        return null;
    }
};

const AuthVerify = (props:any) => {
    let location = props.router.location;

    useEffect(() => {
        // @ts-ignore
        const user = JSON.parse(localStorage.getItem("user"));

        if (user) {
            const decodedJwt = parseJwt(user.accessToken);

            if (decodedJwt.exp * 1000 < Date.now()) {
                props.logOut();
            }
        }
    }, [location]);

    return <div></div>;
};

export default withRouter(AuthVerify);