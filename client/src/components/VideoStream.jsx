import VideoPlayer from "./VideoPlayer";
import { useRef } from "react";

const VideoStream = () => {
  const playerRef = useRef(null);
  const videoLink =
    "https://res.cloudinary.com/dwxebq3fh/video/upload/sp_auto/v1715516553/w1ef9v8zvrzyyavnbyxm.m3u8";
  const videoPlayerOptions = {
    controls: true,
    responsive: true,
    fluid: true,
    sources: [
      {
        src: videoLink,
        type: "application/x-mpegURL",
      },
    ],
  };
  const handlePlayerReady = (player) => {
    playerRef.current = player;

    // You can handle player events here, for example:
    player.on("waiting", () => {
      videojs.log("player is waiting");
    });

    player.on("dispose", () => {
      videojs.log("player will dispose");
    });
  };
  return (
    <>
      <div>
        <h1>Video player</h1>
      </div>

      <VideoPlayer options={videoPlayerOptions} onReady={handlePlayerReady} />
    </>
  );
};

export default VideoStream;
