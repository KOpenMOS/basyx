/*
 * any_serializer.h
 *
 *  Created on: 21.03.2019
 *      Author: psota
 */

#ifndef BASYX_SERIALIZATION_JSON_JSON_SERIALIZER_H
#define BASYX_SERIALIZATION_JSON_JSON_SERIALIZER_H

#include <json/json.hpp>

#include <basyx/types.h>

#include <util/array.h>

namespace basyx {
namespace serialization {
namespace json {
    using json_t = nlohmann::json;
    // Unknown type serializer
    // Throw static assertion at compile time
    // signaling that no basyx serializer for this type exists
    //template<typename T>
    //void serialize_helper(json_t & json, const T &, typename std::enable_if<!std::is_fundamental<T>::value, T>::type = 0) {
    //	static_assert(false, "No basyx serialization for type exists!");
    //};

    // Fundamental type serializer
    // int, float, double, char, etc.
    template <typename T>
    //		inline void serialize_helper(json_t & json, const T & value, typename std::enable_if<std::is_fundamental<T>::value, T>::type = 0) {
    inline void serialize_helper(json_t& json, const T& value)
    {
        //json = json_t{
        //	{ basyx::serialization::typeIdSpecifier, basyx::serialization::basysType<T>::string},
        //	{ basyx::serialization::typeSpecifier, "value" },
        //	{ basyx::serialization::valueSpecifier, value }
        //};
        json = value;
    };
    // std::string serializer
    // isn't a fundamental type, so needs own serialization handling
    inline void serialize_helper(json_t& json, const std::string& string)
    {
        //json = json_t{
        //	{ basyx::serialization::typeIdSpecifier, basyx::serialization::basysType<std::string>::string},
        //	{ basyx::serialization::typeSpecifier, "value" },
        //	{ basyx::serialization::valueSpecifier, string }
        //};
        json = string;
    };

    // basyx::any serializer
    inline void serialize_helper(json_t& json, const basyx::any& any)
    {
        json = any;
    };

    // basyx::array serializer
    template <typename T>
    inline void serialize_helper(json_t& json, const basyx::array<T>& array)
    {
        // serialize header
        json = json_t {
            { basyx::serialization::typeSpecifier, basyx::serialization::arraySpecifier },
            { basyx::serialization::sizeSpecifier, array.size() },
            { basyx::serialization::arrayTypeSpecifier, basyx::serialization::basysType<T>::string }
        };

        // serialize items
        for (std::size_t i = 0; i < array.size(); ++i) {
            json_t value;
            serialize_helper(value, array.get(i));
            json[std::to_string(i)] = value;
        }
    }

    // basyx::objectMap_t serializer
    inline void serialize_helper(json_t& json, const basyx::objectMap_t& objectMap)
    {
        //json = json_t{
        //	{ basyx::serialization::typeSpecifier, "map"},
        //	{ basyx::serialization::sizeSpecifier, objectMap.size() }
        //};

        json_t collectionTypes;

        for (const auto& entry : objectMap) {
            if (entry.second.InstanceOf<basyx::objectCollection_t>()) {
                collectionTypes[entry.first] = "list";
            };

            json[entry.first] = entry.second;
            if (collectionTypes.size() > 0)
                json["_basyxTypes"] = collectionTypes;
        }
    }

    //// basyx::objectSet_t serializer
    //inline void serialize_helper(json_t & json, const basyx::objectSet_t & objectSet)
    //{
    //	json = json_t{
    //		{ basyx::serialization::typeSpecifier, "set"},
    //		{ basyx::serialization::sizeSpecifier, objectSet.size() }
    //	};

    //	std::size_t index = 0;
    //	for (const auto & item : objectSet) {
    //		json[std::to_string(index++)] = item;
    //	};
    //}

    // basyx::objectCollection_t serializer
    inline void serialize_helper(json_t& json, const basyx::objectCollection_t& objectCollection)
    {
        for (const auto& object : objectCollection) {
            json.push_back(object);
        }
    }

};
};
};

#endif /* BASYX_SERIALIZATION_JSON_JSON_SERIALIZER_H */