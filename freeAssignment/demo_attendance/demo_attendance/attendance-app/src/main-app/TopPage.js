import React from "react";
import {Box, Tab, Tabs} from "@mui/material";
import PropTypes from "prop-types";

const TabPanel = (props) => {
    const {children, value, index, ...other} = props;
    return (
        <div
            role="tabpanel"
            hidden={value !== index}
            id={`simple-tabpanel-${index}`}
            aria-required={`simple-tab-${index}`}
            {...other}
        >
            {value === index && <div>{children}</div>}
        </div>
    )
}

TabPanel.propTypes = {
    children: PropTypes.node, index: PropTypes.number.isRequired, value: PropTypes.number.isRequired,
};

const a11yProps = (index) => {
    return {
        id: `simple-tab-${index}`, 'aria-controls': `simple-tabpanel-${index}`,
    };
}

const TopPage = () => {
    return (
        <>
            <header>

            </header>
            <div>
                <h2>出退勤管理システム</h2>
                <div>
                    <Tabs>
                        <Tab label="勤務実績管理"></Tab>
                        <Tab label="運用業務"></Tab>
                        <Tab label="マスタ管理"></Tab>
                        <Tab label="基本情報設定"></Tab>
                        <Tab label="＊"></Tab>
                        <Tab label="＊"></Tab>
                        <Tab label="お知らせ"></Tab>
                    </Tabs>
                </div>

            </div>
        </>
    )
}

export default TopPage