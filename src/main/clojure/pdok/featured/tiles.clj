(ns pdok.featured.tiles
  (:require [pdok.featured.feature :as f])
  (:import [pdok.featured.tiles NLTile]
           [com.vividsolutions.jts.geom Geometry Coordinate]
           [pdok.featured GeometryAttribute]))

(defn nl [^GeometryAttribute geometry]
  "Returns a set with NL tiles (numbers) based on the geometry."
      (if-let [tiles (when geometry (.getTiles geometry))]
              tiles
              (when-let [jts-geom (f/as-jts geometry)]
                        (let [coordinates (.getCoordinates ^Geometry jts-geom)]
                             (into #{} (filter #(not= -1 %)
                                               (map (fn [^Coordinate coordinate]
                                                        (NLTile/getTileFromRD (.x coordinate) (.y coordinate))) coordinates))))))
      )