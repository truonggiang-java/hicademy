import * as React from "react";
import Grid from "@mui/material/Grid";
import Container from "@mui/material/Container";
import ReactPlayer from "react-player";
import List from "@mui/material/List";
import ListItemButton from "@mui/material/ListItemButton";
import ListSubheader from "@mui/material/ListSubheader";
import "../App.css";
import "../assets/style/audio.css";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "../utils/axios";

function Audio() {
  const params = useParams();
  const [audio, setAudio] = useState([]);
  const [links, setLinks] = useState();
  const [changeAudio, setChangeAudio] = useState();

  useEffect(() => {
    const audio = changeAudio ? changeAudio : "music";
    axios.get(`/api/v2/video/findVideoByParam?param=${audio}`).then((res) => {
      setAudio(res.data);
    });

    console.log(123);
  }, [changeAudio]);

  function now(current) {
    return setLinks(current.link), console.log(links);
  }

  const Story = () => {
    setChangeAudio("story");
  };

  const Music = () => {
    setChangeAudio("music");
  };

  return (
    <React.Fragment>
      <div style={{ minHeight: "calc(100vh - 250px)" }}>
        <Container maxWidth="xl">
          <Grid
            container
            spacing={2}
            style={{ fontFamily: "initial", justifyItems: "center" }}
          >
            <Grid
              item
              xs={2}
              style={{
                textAlign: "center",
                display: "flex",
                justifyContent: "center",
                paddingTop: "50px",
              }}
            >
              <div className="w-full">
                <button className="btnaudio" onClick={() => Music()}>
                  {" "}
                  Music{" "}
                </button>
                <br />
                <button className="btnaudio" onClick={() => Story()}>
                  {" "}
                  Story{" "}
                </button>
              </div>
            </Grid>

            <Grid
              item
              xs={3}
              style={{
                textAlign: "center",
                display: "flex",
                justifyContent: "center",
                paddingTop: "50px",
              }}
            >
              <List
                sx={{
                  width: "100%",
                  maxWidth: 360,
                  bgcolor: "background.paper",
                  borderRadius: "20px",
                  overflow: "auto",
                  maxHeight: 360,
                }}
                component="nav"
                aria-labelledby="nested-list-subheader"
                subheader={
                  <ListSubheader
                    component="div"
                    id="nested-list-subheader"
                    className="font"
                    style={{ fontSize: "30px" }}
                  >
                    Play List
                  </ListSubheader>
                }
              >
                {audio.map((current) => (
                  <ListItemButton onClick={() => now(current)} key={current.id}>
                    <div>
                      {current.name}
                    </div>
                  </ListItemButton>
                ))}
              </List>
            </Grid>

            <Grid
              item
              xs={7}
              style={{
                textAlign: "center",
                display: "flex",
                justifyContent: "center",
                paddingTop: "50px",
              }}
            >
              <div style={{ borderRadius: "20px", overflow: "hidden" }}>
                <ReactPlayer
                  controls={true}
                  url={links || '"https://youtu.be/cjj1FULDTA8"'}
                />
              </div>
            </Grid>
          </Grid>
        </Container>
      </div>
    </React.Fragment>
  );
}

export default Audio;
