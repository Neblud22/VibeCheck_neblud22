import React from "react";
import { AppBar, Toolbar, Typography, Stack, Button } from "@mui/material";
import { Link } from "react-router-dom";

const NavBar: React.FC = () => {
    return (
        <AppBar position="fixed" sx={{ backgroundColor: "#0f1363", color: "white" }}>
            <Toolbar>
                <Typography variant="h6" sx={{ flexGrow: 1, textAlign: "left" }}>
                    VibeCheck
                </Typography>
                <Stack direction="row" spacing={2}>
                    <Button
                        variant="outlined"
                        component= {Link}
                        to="/"
                        sx={{ borderColor: "#ffffff", color: "#ffffff" }}
                    >
                        Artists
                    </Button>
                    <Button
                        variant="outlined"
                        component={Link}
                        to="/events"
                        sx={{ borderColor: "#ffffff", color: "#ffffff" }}
                    >
                        Events
                    </Button>
                </Stack>
            </Toolbar>
        </AppBar>
    );
};

export default NavBar;